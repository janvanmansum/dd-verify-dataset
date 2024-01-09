/*
 * Copyright (C) 2022 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.verifydataset.core;

import nl.knaw.dans.lib.dataverse.DataverseClient;
import nl.knaw.dans.lib.dataverse.DataverseException;
import nl.knaw.dans.lib.dataverse.model.file.FileMeta;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;

import java.io.IOException;
import java.util.List;

public class DataverseClientWrapper {
    private final DataverseClient dataverseClient;

    public DataverseClientWrapper(DataverseClient dataverseClient) {
        this.dataverseClient = dataverseClient;
    }

    protected List<FileMeta> getFiles(String pid) throws IOException, DataverseException {
        return dataverseClient
            .dataset(pid).getLatestVersion().getData().getLatestVersion()
            .getFiles();
    }

    protected String getFile(int fileId) throws DataverseException, IOException {
        BasicHttpClientResponseHandler handler = new BasicHttpClientResponseHandler();
        return dataverseClient.basicFileAccess(fileId).getFile(handler);
    }
}
