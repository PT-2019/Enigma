import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Crée un exécutable
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 20/02/2020
 * @since 6.0 20/02/2020
 */
public class Pack {

	private static final String JDK = "exe/jdk/";
	private static final String EXE = "enigma";
	private static final String JAR_PATH = "exe/Enigma.jar";
	private static final String MAIN_CLASS = "Main";
	private static final String[] ARGS = {"Xmx1G"};
	private static final String MINIMISE = "soft";//hard
	private static final String ICON = "assets/logo.icns";

	public static void main(String[] args) throws IOException {
		Window64.main(args);
	}

	/**
	 * Windows64
	 */
	public static final class Window64 {

		public static void main(String[] args) throws IOException {
			PackrConfig config = new PackrConfig();
			config.platform = PackrConfig.Platform.Windows64;
			config.jdk = JDK;
			config.executable = EXE;
			config.classpath = Collections.singletonList(JAR_PATH);
			//garde que les libs de windows
			//config.removePlatformLibs = config.classpath;
			config.mainClass = MAIN_CLASS;
			config.vmArgs = Arrays.asList(ARGS);
			config.minimizeJre = MINIMISE;
			config.iconResource = new java.io.File(ICON);
			config.outDir = new java.io.File("exe/windows64");

			new Packr().pack(config);
		}
	}
}
