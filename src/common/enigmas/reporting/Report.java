package common.enigmas.reporting;

import java.util.ArrayList;

/**
 * Le message a reporter
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1 12/02/2020
 * @since 6.0 12/02/2020
 */
public interface Report {

    /* Importance : 
     * 0 : signifie que le rapport ne semble pas disposer d'intérêt à être révélé au joueur
     * 1 : signifie qu'il existe peut être un intérêt à révéler le rapport au joueur
     * 2 ou plus : signifie qu'il existe un intérêt clair à révéler le rapport au joueur
     */

    /**
     * signifie que le rapport ne semble pas disposer d'intérêt à être révélé au joueur
     */
    int SHOULD_NOT_BE_SHOWED = 0;
    /**
     * signifie qu'il existe peut être un intérêt à révéler le rapport au joueur
     */
    int MAY_BE_SHOWED = 1;

    /**
     * Message sur la réalisation de l'action
     *
     * @return Message sur la réalisation de l'action
     */
    String getReport();

    /**
     * Obtenir l'importance
     *
     * @return Importance
     */
    int getImportance();

    /**
     * Trie par ordre d'importance
     *
     * @param reports Rapports
     */
    static void sort(ArrayList<Report> reports){
        for (int i = 0; i < reports.size(); i++) {
            for (int j = i, k = i - 1; k >= 0; j--, k--) {
                Report reportJ = reports.get(j);
                Report reportK = reports.get(k);

                if(reportJ.getImportance() < reportK.getImportance()) {
                    reports.set(j,reportK);
                    reports.set(k,reportJ);
                }
            }
        }
    }

    /**
     * Trie par ordre d'importance
     *
     * @param reports Rapports
     */
    static void sort(Report[] reports){
        for (int i = 0; i < reports.length; i++) {
            for (int j = i, k = i - 1; k >= 0; j--, k--) {
                Report reportJ = reports[j];
                Report reportK = reports[k];

                if(reportJ.getImportance() < reportK.getImportance()) {
                    reports[j] = reportK;
                    reports[k] = reportJ;
                }
            }
        }
    }
}
