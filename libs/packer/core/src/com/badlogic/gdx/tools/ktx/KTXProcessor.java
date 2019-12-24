
package com.badlogic.gdx.tools.ktx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.ETC1;
import com.badlogic.gdx.graphics.glutils.ETC1.ETC1Data;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.zip.GZIPOutputStream;

public class KTXProcessor {
    private final static byte[] HEADER_MAGIC = {(byte) 0x0AB, (byte) 0x04B, (byte) 0x054, (byte) 0x058, (byte) 0x020, (byte) 0x031,
            (byte) 0x031, (byte) 0x0BB, (byte) 0x00D, (byte) 0x00A, (byte) 0x01A, (byte) 0x00A};

    private final static int DISPOSE_DONT = 0;
    private final static int DISPOSE_PACK = 1;
    private final static int DISPOSE_FACE = 2;
    private final static int DISPOSE_LEVEL = 4;

    private static final TempFileAccessor fileAccessorEtcTools = new TempExecutableFileAccessor(
            SharedLibraryLoader.isWindows ? "etctool/etctool.exe" :
            SharedLibraryLoader.isLinux ? "etctool/etctool-linux" :
            SharedLibraryLoader.isMac ? "etctool/etctool-mac" : null
            , 0);

    public static void convert(String input, String output, String ETC1comp, String ETC2comp) throws Exception {
        Array<String> opts = new Array<String>(String.class);
        opts.add(input);
        opts.add(output);
        if (ETC1comp != null) opts.add(ETC1comp);
        if (ETC2comp != null) opts.add(ETC2comp);
        KTXProcessorListener.init(opts.toArray());
    }

    public static class KTXProcessorListener extends ApplicationAdapter {
        String[] args;

        KTXProcessorListener(String[] args) {
            this.args = args;
        }

        @Override
        public void create() {
            init(args);
            Gdx.app.exit();
        }

        public static void init(String[] args) {
            boolean isCubemap = args.length == 7 || args.length == 8 || args.length == 9;
            boolean isTexture = args.length == 2 || args.length == 3 || args.length == 4 || args.length == 5;
            boolean isPackETC1 = false, isAlphaAtlas = false, isGenMipMaps = false, useEtc2Comp = false;
            if (!isCubemap && !isTexture) {
                System.out.println("usage : KTXProcessor input_file output_file [-etc1|-etc1a] [-mipmaps]");
                System.out.println("  input_file  is the texture file to include in the output KTX or ZKTX file.");
                System.out
                        .println("              for cube map, just provide 6 input files corresponding to the faces in the following order : X+, X-, Y+, Y-, Z+, Z-");
                System.out
                        .println("  output_file is the path to the output file, its type is based on the extension which must be either KTX or ZKTX");
                System.out.println();
                System.out.println("  options:");
                System.out.println("    -etc1    input file will be packed using ETC1 compression, dropping the alpha channel");
                System.out
                        .println("    -etc1a   input file will be packed using ETC1 compression, doubling the height and placing the alpha channel in the bottom half");
                System.out
                        .println("    -RGB8, -SRGB8, -RGBA8, -SRGB8, -RGB8A1, -SRGB8A1, -R11   input file will be packed using ETC2 compression and specified format");
                System.out.println("    -mipmaps input file will be processed to generate mipmaps");
                System.out.println();
                System.out.println("  examples:");
                System.out
                        .println("    KTXProcessor in.png out.ktx                                        Create a KTX file with the provided 2D texture");
                System.out
                        .println("    KTXProcessor in.png out.zktx                                       Create a Zipped KTX file with the provided 2D texture");
                System.out
                        .println("    KTXProcessor in.png out.zktx -mipmaps                              Create a Zipped KTX file with the provided 2D texture, generating all mipmap levels");
                System.out
                        .println("    KTXProcessor px.ktx nx.ktx py.ktx ny.ktx pz.ktx nz.ktx out.zktx    Create a Zipped KTX file with the provided cubemap textures");
                System.out
                        .println("    KTXProcessor in.ktx out.zktx                                       Convert a KTX file to a Zipped KTX file");
                System.exit(-1);
            }

//            LwjglNativesLoader.load();

            String etc2Format = "RGBA8";
            String[] et2Attr = {"-RGB8", "-SRGB8", "-RGBA8", "-SRGB8", "-RGB8A1", "-SRGB8A1", "-R11"};

            // Loads other options
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + " = " + args[i]);
                if (isTexture && i < 2) continue;
                if (isCubemap && i < 7) continue;
                if ("-etc1".equals(args[i])) isPackETC1 = true;
                if ("-etc1a".equals(args[i])) isAlphaAtlas = isPackETC1 = true;
                if ("-mipmaps".equals(args[i])) isGenMipMaps = true;
                for (String format : et2Attr) {
                    if (format.equals(args[i])) {
                        useEtc2Comp = true;
                        etc2Format = format;
                    }
                }
            }

            File output = new File(args[isCubemap ? 6 : 1]);

            if (!isPackETC1 && !isGenMipMaps && useEtc2Comp) {
                File etc2File = executeEtc2Comp(args[0], output.getAbsolutePath(), etc2Format);
                if (output.getName().toLowerCase().endsWith(".zktx"))
                    if (etc2File != null)
                        try {
                            gzip(etc2File.getAbsolutePath(), etc2File.getAbsolutePath().replace("ktx", "zktx"));
                        } catch (IOException e) {
                            throw new RuntimeException("Error zipping file: " + output.getName(), e);
                        }
                return;
            }

            // Check if we have a cubemapped ktx file as input
            int ktxDispose = DISPOSE_DONT;
            KTXTextureData ktx = null;
            FileHandle file = new FileHandle(args[0]);
            if (file.name().toLowerCase().endsWith(".ktx") || file.name().toLowerCase().endsWith(".zktx")) {
                ktx = new KTXTextureData(file, false);
                if (ktx.getNumberOfFaces() == 6) isCubemap = true;
                ktxDispose = DISPOSE_PACK;
            }

            // Process all faces
            int nFaces = isCubemap ? 6 : 1;
            Image[][] images = new Image[nFaces][];
            int texWidth = -1, texHeight = -1, texFormat = -1, nLevels = 0;
            for (int face = 0; face < nFaces; face++) {
                ETC1Data etc1 = null;
                Pixmap facePixmap = null;
                int ktxFace = 0;

                // Load source image (ends up with either ktx, etc1 or facePixmap initialized)
                if (ktx != null && ktx.getNumberOfFaces() == 6) {
                    // No loading since we have a ktx file with cubemap as input
                    nLevels = ktx.getNumberOfMipMapLevels();
                    ktxFace = face;
                } else {
                    file = new FileHandle(args[face]);
                    System.out.println("Processing : " + file + " for face #" + face);
                    if (file.name().toLowerCase().endsWith(".ktx") || file.name().toLowerCase().endsWith(".zktx")) {
                        if (ktx == null || ktx.getNumberOfFaces() != 6) {
                            ktxDispose = DISPOSE_FACE;
                            ktx = new KTXTextureData(file, false);
                            ktx.prepare();
                        }
                        nLevels = ktx.getNumberOfMipMapLevels();
                        texWidth = ktx.getWidth();
                        texHeight = ktx.getHeight();
                    } else if (file.name().toLowerCase().endsWith(".etc1")) {
                        etc1 = new ETC1Data(file);
                        nLevels = 1;
                        texWidth = etc1.width;
                        texHeight = etc1.height;
                    } else {
                        facePixmap = new Pixmap(file);
                        facePixmap.setBlending(Blending.None);
                        facePixmap.setFilter(Filter.BiLinear);
                        nLevels = 1;
                        texWidth = facePixmap.getWidth();
                        texHeight = facePixmap.getHeight();
                    }
                    if (isGenMipMaps) {
                        if (!MathUtils.isPowerOfTwo(texWidth) || !MathUtils.isPowerOfTwo(texHeight))
                            throw new GdxRuntimeException(
                                    "Invalid input : mipmap generation is only available for power of two textures : " + file);
                        nLevels = Math.max(Integer.SIZE - Integer.numberOfLeadingZeros(texWidth),
                                Integer.SIZE - Integer.numberOfLeadingZeros(texHeight));
                    }
                }

                // Process each mipmap level
                images[face] = new Image[nLevels];
                for (int level = 0; level < nLevels; level++) {
                    int levelWidth = Math.max(1, texWidth >> level);
                    int levelHeight = Math.max(1, texHeight >> level);

                    // Get pixmap for this level (ends with either levelETCData or levelPixmap being non null)
                    Pixmap levelPixmap = null;
                    ETC1Data levelETCData = null;
                    if (ktx != null) {
                        ByteBuffer ktxData = ktx.getData(level, ktxFace);
                        if (ktxData != null && ktx.getGlInternalFormat() == ETC1.ETC1_RGB8_OES)
                            levelETCData = new ETC1Data(levelWidth, levelHeight, ktxData, 0);
                    }
                    if (ktx != null && levelETCData == null && facePixmap == null) {
                        ByteBuffer ktxData = ktx.getData(0, ktxFace);
                        if (ktxData != null && ktx.getGlInternalFormat() == ETC1.ETC1_RGB8_OES)
                            facePixmap = ETC1.decodeImage(new ETC1Data(levelWidth, levelHeight, ktxData, 0), Format.RGB888);
                    }
                    if (level == 0 && etc1 != null) {
                        levelETCData = etc1;
                    }
                    if (levelETCData == null && etc1 != null && facePixmap == null) {
                        facePixmap = ETC1.decodeImage(etc1, Format.RGB888);
                    }
                    if (levelETCData == null) {
                        levelPixmap = new Pixmap(levelWidth, levelHeight, facePixmap.getFormat());
                        levelPixmap.setBlending(Blending.None);
                        levelPixmap.setFilter(Filter.BiLinear);
                        levelPixmap.drawPixmap(facePixmap, 0, 0, facePixmap.getWidth(), facePixmap.getHeight(), 0, 0,
                                levelPixmap.getWidth(), levelPixmap.getHeight());
                    }
                    if (levelETCData == null && levelPixmap == null)
                        throw new GdxRuntimeException("Failed to load data for face " + face + " / mipmap level " + level);

                    // Create alpha atlas
                    if (isAlphaAtlas) {
                        if (levelPixmap == null) levelPixmap = ETC1.decodeImage(levelETCData, Format.RGB888);
                        int w = levelPixmap.getWidth(), h = levelPixmap.getHeight();
                        Pixmap pm = new Pixmap(w, h * 2, levelPixmap.getFormat());
                        pm.setBlending(Blending.None);
                        pm.setFilter(Filter.BiLinear);
                        pm.drawPixmap(levelPixmap, 0, 0);
                        for (int y = 0; y < h; y++) {
                            for (int x = 0; x < w; x++) {
                                int alpha = (levelPixmap.getPixel(x, y)) & 0x0FF;
                                pm.drawPixel(x, y + h, (alpha << 24) | (alpha << 16) | (alpha << 8) | 0x0FF);
                            }
                        }
                        levelPixmap.dispose();
                        levelPixmap = pm;
                        levelETCData = null;
                    }

                    // Perform ETC1 compression
                    if (levelETCData == null && isPackETC1) {
                        if (levelPixmap.getFormat() != Format.RGB888 && levelPixmap.getFormat() != Format.RGB565) {
                            if (!isAlphaAtlas)
                                System.out.println("Converting from " + levelPixmap.getFormat() + " to RGB888 for ETC1 compression");
                            Pixmap tmp = new Pixmap(levelPixmap.getWidth(), levelPixmap.getHeight(), Format.RGB888);
                            tmp.setBlending(Blending.None);
                            tmp.setFilter(Filter.BiLinear);
                            tmp.drawPixmap(levelPixmap, 0, 0, 0, 0, levelPixmap.getWidth(), levelPixmap.getHeight());
                            levelPixmap.dispose();
                            levelPixmap = tmp;
                        }
                        // System.out.println("Compress : " + levelWidth + " x " + levelHeight);
                        levelETCData = ETC1.encodeImagePKM(levelPixmap);
                        levelPixmap.dispose();
                        levelPixmap = null;
                    }

                    // Save result to ouput ktx
                    images[face][level] = new Image();
                    images[face][level].etcData = levelETCData;
                    images[face][level].pixmap = levelPixmap;
                    if (levelPixmap != null) {
                        levelPixmap.dispose();
                        facePixmap = null;
                    }
                }

                // Dispose resources
                if (facePixmap != null) {
                    facePixmap.dispose();
                    facePixmap = null;
                }
                if (etc1 != null) {
                    etc1.dispose();
                    etc1 = null;
                }
                if (ktx != null && ktxDispose == DISPOSE_FACE) {
                    ktx.disposePreparedData();
                    ktx = null;
                }
            }
            if (ktx != null) {
                ktx.disposePreparedData();
                ktx = null;
            }

            int glType, glTypeSize, glFormat, glInternalFormat, glBaseInternalFormat;
            if (isPackETC1) {
                glType = glFormat = 0;
                glTypeSize = 1;
                glInternalFormat = ETC1.ETC1_RGB8_OES;
                glBaseInternalFormat = GL20.GL_RGB;
            } else if (images[0][0].pixmap != null) {
                glType = images[0][0].pixmap.getGLType();
                glTypeSize = 1;
                glFormat = images[0][0].pixmap.getGLFormat();
                glInternalFormat = images[0][0].pixmap.getGLInternalFormat();
                glBaseInternalFormat = glFormat;
            } else
                throw new GdxRuntimeException("Unsupported output format");

            int totalSize = 12 + 13 * 4;
            for (int level = 0; level < nLevels; level++) {
                System.out.println("Level: " + level);
                int faceLodSize = images[0][level].getSize();
                int faceLodSizeRounded = (faceLodSize + 3) & ~3;
                totalSize += 4;
                totalSize += nFaces * faceLodSizeRounded;
            }

            File etc2File = null;
            if (useEtc2Comp) etc2File = executeEtc2Comp(args[0], output.getAbsolutePath(), etc2Format);

            try {
                DataOutputStream out;
                if (output.getName().toLowerCase().endsWith(".zktx")) {
                    if (etc2File != null)
                        gzip(etc2File.getAbsolutePath(), etc2File.getAbsolutePath().replace("ktx", "zktx"));
                    out = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                    out.writeInt(totalSize);
                } else
                    out = new DataOutputStream(new FileOutputStream(output));

                out.write(HEADER_MAGIC);
                out.writeInt(0x04030201);
                out.writeInt(glType);
                out.writeInt(glTypeSize);
                out.writeInt(glFormat);
                out.writeInt(glInternalFormat);
                out.writeInt(glBaseInternalFormat);
                out.writeInt(texWidth);
                out.writeInt(isAlphaAtlas ? (2 * texHeight) : texHeight);
                out.writeInt(0); // depth (not supported)
                out.writeInt(0); // n array elements (not supported)
                out.writeInt(nFaces);
                out.writeInt(nLevels);
                out.writeInt(0); // No additional info (key/value pairs)
                for (int level = 0; level < nLevels; level++) {
                    int faceLodSize = images[0][level].getSize();
                    int faceLodSizeRounded = (faceLodSize + 3) & ~3;
                    out.writeInt(faceLodSize);
                    for (int face = 0; face < nFaces; face++) {
                        byte[] bytes = images[face][level].getBytes();
                        out.write(bytes);
                        for (int j = bytes.length; j < faceLodSizeRounded; j++)
                            out.write((byte) 0x00);
                    }
                }

                out.close();
                System.out.println("Finished");
            } catch (Exception e) {
                throw new RuntimeException("Error writing to file: " + output.getName(), e);
            }
        }
    }

    public static File executeEtc2Comp(String filePath, String outputPath, String etc2Format) {
        try {
            final URI exe = fileAccessorEtcTools.getExecutableFile().toURI();

            int index = outputPath.lastIndexOf(".");
            if (index >= 0) outputPath = outputPath.substring(0, index) + etc2Format + ".ktx";

            System.out.println("Starting etc2comp");
            String[] cmd = {exe.getPath(), filePath, "-format", etc2Format.replace("-", ""), "-output", outputPath};
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int result = process.waitFor(); // Let the process finish.
            if (result == 0) {
                System.out.println("etc2comp finished");
                return new File(outputPath);
            } else {
                throw new RuntimeException("Error executing etc2comp command, result code: " + result);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error executing etc2comp command: ", e);
        }
    }

    public static void gzip(String inFile, String outFile) throws FileNotFoundException, IOException {
        FileInputStream read = null;
        DataOutputStream write = null;
        try {
            File in = new File(inFile), out = new File(outFile);
            int writtenBytes = 0, length = (int) in.length();
            byte[] buffer = new byte[10 * 1024];
            read = new FileInputStream(in);
            write = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(out)));
            write.writeInt(length);
            while (writtenBytes != length) {
                int nBytesRead = read.read(buffer);
                write.write(buffer, 0, nBytesRead);
                writtenBytes += nBytesRead;
            }
        } finally {
            if (write != null) write.close();
            if (read != null) read.close();
        }
    }

    private static class Image {

        public ETC1Data etcData;
        public Pixmap pixmap;

        public Image() {
        }

        public int getSize() {
            if (etcData != null) return etcData.compressedData.limit() - etcData.dataOffset;
            throw new GdxRuntimeException("Unsupported output format, try adding '-etc1' as argument");
        }

        public byte[] getBytes() {
            if (etcData != null) {
                byte[] result = new byte[getSize()];
                etcData.compressedData.position(etcData.dataOffset);
                etcData.compressedData.get(result);
                return result;
            }
            throw new GdxRuntimeException("Unsupported output format, try adding '-etc1' as argument");
        }
    }

    /** Extracts and stores internal resource file reference. */
    private static class TempFileAccessor {
        protected static final String PREFERENCES_FILE = "ktx_processor.xml";
        protected static final String PREF_REVISION_SUFFIX = "-revision";

        protected final String fileClassPath;
        protected final int fileRevision;

        protected final String prefKeyFilePath;
        protected final String prefKeyFileRevision;

        protected File tempFile = null;

        /**
         *  @param fileClassPath Target file's class path.
         *  @param fileRevision Number that is stored inside preferences and indicates revision of extracted file.
         *                      This is useful when resource file was changed and needs to be overwritten.
         */
        public TempFileAccessor(String fileClassPath, int fileRevision) {
            this.fileClassPath = fileClassPath;
            this.fileRevision = fileRevision;

            prefKeyFilePath = fileClassPath;
            prefKeyFileRevision = fileClassPath + PREF_REVISION_SUFFIX;
        }

        public synchronized File getExecutableFile() {
            // Check if file previously was extracted and ready to be reused
            if (tempFile != null && tempFile.exists()) return tempFile;

            tempFile = loadTempFilePath();
            if (tempFile != null && tempFile.exists()) return tempFile;

            tempFile = copyToTempFile(fileClassPath);
            if (tempFile != null) {
                System.out.println("File " + fileClassPath + " temporary extracted to " + tempFile);
                saveTempFilePath(tempFile);
                return tempFile;
            }

            throw new IllegalStateException("Can't access/extract file: " + fileClassPath);
        }

        protected void saveTempFilePath(File file) {
            try {
                Preferences preferences = Gdx.app.getPreferences(PREFERENCES_FILE);
                preferences
                        .putString(prefKeyFilePath, file.getAbsolutePath())
                        .putInteger(prefKeyFileRevision, fileRevision)
                        .flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected File loadTempFilePath() {
            try {
                Preferences preferences = Gdx.app.getPreferences(PREFERENCES_FILE);
                int revision = preferences.getInteger(prefKeyFileRevision, -1);
                // Check if the revisions are the same
                if (this.fileRevision != revision) return null;

                String absolutePath = preferences.getString(prefKeyFilePath, null);
                if (absolutePath != null) {
                    return new File(absolutePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected File copyToTempFile(String resourcePath) {
            File temp;
            InputStream in = null;
            FileOutputStream fos = null;
            try {
                in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
                byte[] buffer = new byte[1024];
                int read;
                temp = File.createTempFile(resourcePath, "");
                fos = new FileOutputStream(temp);

                while ((read = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, read);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ignored) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ignored) {
                    }
                }
            }
            return temp;
        }
    }

    private static class TempExecutableFileAccessor extends TempFileAccessor {
        /** @see TempFileAccessor#TempFileAccessor(String, int) */
        public TempExecutableFileAccessor(String fileClassPath, int fileRevision) {
            super(fileClassPath, fileRevision);
        }

        @Override
        protected File copyToTempFile(String resourcePath) {
            File file = super.copyToTempFile(resourcePath);
            if (file == null) return null;

            if (SharedLibraryLoader.isLinux || SharedLibraryLoader.isMac) {
                try {
                    // Change access permission for temp file
                    System.out.println("Call \"chmod\" for a temp file");
                    Process process = Runtime.getRuntime().exec("chmod +x " + file.getAbsolutePath());

                    InputStream is = process.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    int result = process.waitFor(); // Let the process finish.

                    if (result != 0) {
                        throw new RuntimeException("\"chmod\" call finished with error");
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException("Error changing file access permission for: " + file.getAbsolutePath(), e);
                }
            }
            return file;
        }
    }
}
