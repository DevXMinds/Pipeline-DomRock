package com.devxminds.donpipe.loggenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A classe LogGenerator gera logs para cada
 * interação do usuário com a DB, como:
 * Upload, Exclusão ou Edição de um arquivo.
 *
 * @author Gabriel
 * @version 0.1
 *
 */
public class LogGenerator {
    /**
     * O método pega o nome do usuário, nome do arquivo, a ação do usuário e o estagio que o arquivo se encontra.
     * Ele junta todas essas informações em uma única string
     * e retorna um log com as informações compactadas.
     * @param user, nome do usuário
     * @param fileName, nome do arquivo
     * @param action, ação do usuário (upload, edição, exclusão)
     * @param stage, estágio do arquivo (landing zone, bronze, silver)
     * @return log com nome do usuário, nome do arquivo, ação do usuário, estágio do arquivo, data e hora.
     *
     */
    public static String generateLog(String user, String fileName, String action, String stage) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        return user +
                "-" + fileName +
                "-" + action +
                "-" + stage +
                "-" + timestamp;
    }
}
