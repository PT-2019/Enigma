package common.save;

import api.ui.CustomWindow;
import api.utils.Utility;
import common.data.GameData;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaProgressPopup;
import common.utils.EnigmaUtility;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import editor.EditorLauncher;
import game.EnigmaGame;
import game.EnigmaGameLauncher;
import game.hmi.ContentManager;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Exporteur de map
 * Exporte : les enigmes, la map, les données de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.6 01 fevrier 2020
 * @since 6.0
 */
public class ImportExport {
	/**
	 * Textes
	 */
	private static final String REPLACE_FILE = NeedToBeTranslated.REPLACE_FILE;
	private static final String REPLACE_MAP = NeedToBeTranslated.REPLACE_MAP;
	private static final String REPLACE_GAME = NeedToBeTranslated.REPLACE_GAME;
	private static final String EXPORT = NeedToBeTranslated.EXPORTATION;
	private static final String EXPORT_ENDED = NeedToBeTranslated.EXPORT_ENDED;
	private static final String EXPORT_ERROR = NeedToBeTranslated.EXPORT_ERROR;
	private static final String EXPORT_ABANDONED = NeedToBeTranslated.EXPORT_ABANDONED;
	private static final String IMPORT = NeedToBeTranslated.IMPORTATION;
	private static final String IMPORT_ENDED = NeedToBeTranslated.IMPORT_ENDED;
	private static final String IMPORT_ERROR = NeedToBeTranslated.IMPORT_ERROR;
	private static final String IMPORT_ABANDONED = NeedToBeTranslated.IMPORT_ABANDONED;

	/**
	 * Indicateur d'une fin de chaine
	 */
	private final static char STRING_END = '\0';

	/**
	 * Exporte une map
	 *
	 * @param mapName    Nom de la map
	 * @param exportPath Chemin où créer le fichier exporté
	 */
	public static void exportMap(String mapName, String exportPath) {
		ExportMap exportMap = new ExportMap(mapName, exportPath);
		exportMap.start();
	}

	/**
	 * Exporte une map
	 *
	 * @param mapName    Nom de la map
	 * @param exportPath Chemin où créer le fichier exporté
	 * @param gameName   nom de la partie
	 */
	public static void exportGame(String mapName, String gameName, String exportPath) {
		ExportGame exportGame = new ExportGame(mapName, gameName, exportPath);
		exportGame.start();
	}

	/**
	 * Importe une map
	 *
	 * @param importPath Chemin du fichier à importer
	 */
	public static void importMap(String importPath) {
		ImportMap importMap = new ImportMap(importPath);
		importMap.start();
	}

	/**
	 * Importe une partie
	 *
	 * @param importPath Chemin du fichier à importer
	 */
	public static void importGame(String importPath) {
		ImportGame importGame = new ImportGame(importPath);
		importGame.start();
	}

	/**
	 * Convertie une chaine de caractères en octets correspondants
	 * true = 1, false = 0
	 *
	 * @param s Chaine
	 * @return Octets correpondants aux caractères
	 */
	private static ArrayList<boolean[]> toBytes(String s) {
		ArrayList<boolean[]> bytes = new ArrayList<>();
		s += STRING_END;

		for (char c : s.toCharArray()) {
			String sByte = Integer.toBinaryString(c);
			int sLen = sByte.length();
			boolean[] bByte = new boolean[8];
			int bLen = bByte.length;

			for (int i = 0; i < bLen; i++) {
				if (i < sLen)
					bByte[(bLen - sLen) + i] = (sByte.charAt(i) == '1');
				else
					bByte[(bLen - i) - 1] = false;
			}

			bytes.add(bByte);
		}

		return bytes;
	}

	/**
	 * Lit des bits en les convertissant en chaine de caractère
	 * S'arrête à la rencontre d'un '\0'
	 *
	 * @param reader Lecteur
	 * @param epp    Affichage de la progression
	 * @param value  valeur actuelle = int[1]
	 * @return Chaine de caractère
	 * @throws IOException En cas d'erreur de lecture
	 */
	private static String readToString(DataInputStream reader, EnigmaProgressPopup epp, int[] value) throws IOException {
		StringBuilder read = new StringBuilder();

		while (true) {
			StringBuilder sByte = new StringBuilder();
			for (int i = 0; i < 8; i++) {
				if (reader.readBoolean())
					sByte.append('1');
				else
					sByte.append('0');
			}
			char c = (char) Integer.parseInt(sByte.toString(), 2);
			value[0] += Character.BYTES;
			epp.update(value[0]);

			if (c == '\0')
				break;

			read.append(c);
		}

		return read.toString();
	}

	/**
	 * Exporte une map
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 06 fevrier 2020
	 * @see common.save.ImportExport
	 * @since 6.0
	 */
	private static class ExportMap extends Thread {
		/**
		 * Nom de la map
		 */
		private String mapName;
		/**
		 * Chemin d'exportation
		 */
		private String exportPath;
		/**
		 * Affichage de la progession
		 */
		private EnigmaProgressPopup epp;

		/**
		 * @param mapName    Nom de la map
		 * @param exportPath Chemin d'exportation
		 */
		public ExportMap(String mapName, String exportPath) {
			this.mapName = mapName;
			this.exportPath = exportPath;
			this.epp = null;
		}

		@Override
		public void run() {
			File file = new File(exportPath + mapName + Config.MAP_EXPORT_EXTENSION);

			try {
				if (file.exists()) {
					if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
							new Dimension(600, 250),
							REPLACE_FILE)) {
						throw new IllegalStateException("Export annulé");
					}
				}

				DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));
				String[] toWrite = new String[4];
				toWrite[0] = mapName;
				toWrite[1] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
				toWrite[2] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
				toWrite[3] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
				int value = 0;
				int sum = 0;
				for (String s : toWrite)
					sum += s.length() + 1;

				epp = new EnigmaProgressPopup(EXPORT, value, sum, EnigmaProgressPopup.PERCENT_TYPE);
				epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
				epp.getWindow().setLocation(CustomWindow.SOUTH);
				epp.begin();

				//Ecriture
				for (String s : toWrite) {
					for (boolean[] tab : ImportExport.toBytes(s)) {
						for (boolean b : tab)
							writer.writeBoolean(b);
						value++;
						epp.update(value);
					}
				}

				writer.close();
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ENDED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ENDED);
				}

			} catch (IllegalStateException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ABANDONED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ABANDONED);
				}

			} catch (IOException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ERROR);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ERROR);
				}

				Logger.printError("ImportExport.java", "export map error: " + e.getMessage());
			} finally {
				if (epp != null)
					epp.end();
			}
		}
	}

	/**
	 * Exporte une partie
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 06 fevrier 2020
	 * @see common.save.ImportExport
	 * @since 6.0
	 */
	private static class ExportGame extends Thread {
		/**
		 * Nom de la map
		 */
		private String mapName;
		/**
		 * Nom de la partie
		 */
		private String gameName;
		/**
		 * Chemin d'exportation
		 */
		private String exportPath;
		/**
		 * Affichage de la progession
		 */
		private EnigmaProgressPopup epp;

		/**
		 * @param mapName    Nom de la map
		 * @param gameName   Nom de la partie
		 * @param exportPath Chemin d'exportation
		 */
		public ExportGame(String mapName, String gameName, String exportPath) {
			this.mapName = mapName;
			this.gameName = gameName;
			this.exportPath = exportPath;
			this.epp = null;
		}

		@Override
		public void run() {
			File file = new File(exportPath + this.gameName + Config.GAME_EXPORT_EXTENSION);

			try {
				if (file.exists()) {
					if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
							new Dimension(600, 250),
							REPLACE_FILE)) {
						throw new IllegalStateException("Export annulé");
					}
				}

				DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));
				String[] toWrite = new String[6];
				toWrite[0] = mapName;
				toWrite[1] = gameName;
				toWrite[2] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
				toWrite[3] = Utility.readFile(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION);
				toWrite[4] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
				toWrite[5] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
				int value = 0;
				int sum = 0;
				for (String s : toWrite)
					sum += s.length() + 1;

				epp = new EnigmaProgressPopup(EXPORT, value, sum, EnigmaProgressPopup.PERCENT_TYPE);
				epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
				epp.getWindow().setLocation(CustomWindow.SOUTH);
				epp.begin();

				//Ecriture
				for (String s : toWrite) {
					for (boolean[] tab : ImportExport.toBytes(s)) {
						for (boolean b : tab)
							writer.writeBoolean(b);
						value++;
						epp.update(value);
					}
				}

				writer.close();
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ENDED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ENDED);
				}

			} catch (IllegalStateException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ABANDONED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ABANDONED);
				}

			} catch (IOException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(EXPORT_ERROR);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), EXPORT_ERROR);
				}

				Logger.printError("ImportExport.java", "export game error: " + e.getMessage());
			} finally {
				if (epp != null)
					epp.end();
			}
		}
	}

	/**
	 * Import une map
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 06 fevrier 2020
	 * @see common.save.ImportExport
	 * @since 6.0
	 */
	private static class ImportMap extends Thread {
		/**
		 * Chemin du fichier à importer
		 */
		private String importPath;
		/**
		 * Affichage de la progession
		 */
		private EnigmaProgressPopup epp;

		/**
		 * @param importPath Chemin du fichier à importer
		 */
		public ImportMap(String importPath) {
			this.importPath = importPath;
			this.epp = null;
		}

		@Override
		public void run() {
			try {
				if (!importPath.endsWith(Config.MAP_EXPORT_EXTENSION))
					throw new IllegalArgumentException("Ce n'est pas un " + Config.MAP_EXPORT_EXTENSION);

				FileInputStream file = new FileInputStream(importPath);
				DataInputStream reader = new DataInputStream(file);
				BufferedWriter writer;
				try {
					String read;
					int[] value = new int[1];
					value[0] = 0;
					int sum = file.available() / (Character.BYTES * 2);
					epp = new EnigmaProgressPopup(IMPORT, value[0], sum, EnigmaProgressPopup.PERCENT_TYPE);
					epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
					epp.getWindow().setLocation(CustomWindow.SOUTH);
					epp.begin();

					//Récupération du nom
					String mapName = ImportExport.readToString(reader, epp, value);

					for (String s : EnigmaUtility.getAllMapNames()) {
						if (s.equals(mapName)) {
							if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
									new Dimension(600, 250),
									REPLACE_MAP)) {
								throw new IllegalStateException("Import annulé");
							}
						}
					}

					//Récupération des données de la map
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					//Récupération de la map
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					//Récupération des énigmes
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					try {
						EnigmaGame.getCurrentScreen().showToast(IMPORT_ENDED);
					} catch (NullPointerException ex) {
						EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ENDED);
					}

				} catch (EOFException e) {
					reader.close();
				}
			} catch (IllegalStateException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(IMPORT_ABANDONED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ABANDONED);
				}

			} catch (IOException | IllegalArgumentException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(IMPORT_ERROR);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ERROR);
				}

				Logger.printError("ImportExport.java", "import map error: " + e.getMessage());
			} finally {
				if (epp != null)
					epp.end();
			}
		}
	}

	/**
	 * Import une partie
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 06 fevrier 2020
	 * @see common.save.ImportExport
	 * @since 6.0
	 */
	private static class ImportGame extends Thread {
		/**
		 * Chemin du fichier à importer
		 */
		private String importPath;
		/**
		 * Affichage de la progession
		 */
		private EnigmaProgressPopup epp;

		/**
		 * @param importPath Chemin du fichier à importer
		 */
		public ImportGame(String importPath) {
			this.importPath = importPath;
			this.epp = null;
		}

		@Override
		public void run() {
			try {
				if (!importPath.endsWith(Config.GAME_EXPORT_EXTENSION))
					throw new IllegalArgumentException("Ce n'est pas un " + Config.GAME_EXPORT_EXTENSION);

				FileInputStream file = new FileInputStream(importPath);
				DataInputStream reader = new DataInputStream(file);
				BufferedWriter writer;
				try {
					String read;
					int[] value = new int[1];
					value[0] = 0;
					int sum = file.available() / (Character.BYTES * 2);
					epp = new EnigmaProgressPopup(IMPORT, value[0], sum, EnigmaProgressPopup.PERCENT_TYPE);
					epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
					epp.getWindow().setLocation(CustomWindow.SOUTH);
					epp.begin();

					//Récupération du nom
					String mapName = ImportExport.readToString(reader, epp, value);
					String gameName = ImportExport.readToString(reader, epp, value);

					for (String s : EnigmaUtility.getAllMapNames()) {
						if (s.equals(mapName)) {
							if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
									new Dimension(600, 250),
									REPLACE_MAP)) {
								throw new IllegalStateException("Import annulé");
							}
						}
					}

					if (gameName.length() > 0) {
						for (String s : EnigmaUtility.getAllGameName()) {
							if (s.equals(gameName)) {
								if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
										new Dimension(600, 250),
										REPLACE_GAME)) {
									throw new IllegalStateException("Import annulé");
								}
							}
						}
					}
					//Récupération des données de la map
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					//Récupération des données du jeu
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					//Récupération de la map
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					//Récupération des énigmes
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
					read = ImportExport.readToString(reader, epp, value);
					writer.write(read);
					writer.close();

					try {
						EnigmaGame.getCurrentScreen().showToast(IMPORT_ENDED);
					} catch (NullPointerException ex) {
						EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ENDED);
					}

					try {
						GameData game = DataSave.readGameData(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION);

						if (game.isMultiPlayer())
							ContentManager.getInstance().refresh(ContentManager.MULTI_STATE);
						else
							ContentManager.getInstance().refresh(ContentManager.SOLO_STATE);
					} catch (IOException e) {
						//ignore
					}

				} catch (EOFException e) {
					reader.close();
				}
			} catch (IllegalStateException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(IMPORT_ABANDONED);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ABANDONED);
				}

			} catch (IOException | IllegalArgumentException e) {
				try {
					EnigmaGame.getCurrentScreen().showToast(IMPORT_ERROR);
				} catch (NullPointerException ex) {
					EnigmaOptionPane.showAlert(EnigmaGameLauncher.getInstance().getWindow(), IMPORT_ERROR);
				}

				Logger.printError("ImportExport.java", "import game error: " + e.getMessage());
			} finally {
				if (epp != null)
					epp.end();
			}
		}
	}
}
