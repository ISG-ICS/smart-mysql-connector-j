/*
 * Copyright (c) 2002, 2020, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.jdbc.smart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmartJDBCClient {

  // TODO - Get the parameter from a configuration file.
  public static final String SMARTJDBC_SERVER_URL = "http://localhost:8000";

  public static String request(String request) throws IOException {
    URL url = new URL(SMARTJDBC_SERVER_URL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; utf-8");
    conn.setRequestProperty("Accept", "application/json");
    conn.setDoOutput(true);
    byte[] input = request.getBytes("utf-8");
    conn.getOutputStream().write(input, 0, input.length);
    if (conn.getResponseCode() != 200) {
      throw new IOException("[SmartJDBCClient->request] response code = " + conn.getResponseCode());
    }
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    StringBuilder response = new StringBuilder();
    String responseLine = null;
    while ((responseLine = bufferedReader.readLine()) != null) {
      response.append(responseLine);
      response.append(System.lineSeparator());
    }
    return response.toString();
  }
}
