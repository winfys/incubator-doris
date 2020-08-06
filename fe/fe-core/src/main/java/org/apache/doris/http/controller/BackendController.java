// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.http.controller;

import org.apache.doris.catalog.Catalog;
import org.apache.doris.http.entity.ResponseEntityBuilder;
import org.apache.doris.system.Backend;

import com.google.common.collect.ImmutableMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/v1")
public class BackendController {
    private static final Logger LOG = LogManager.getLogger(BackendController.class);

    @RequestMapping(path = "/backend", method = RequestMethod.GET)
    public Object backend() {
        ImmutableMap<Long, Backend> backendMap = Catalog.getCurrentSystemInfo().getIdToBackend();
        Map<String, Backend> result = new HashMap<>();
        for (long key : backendMap.keySet()) {
            result.put(Long.toString(key), backendMap.get(key));
        }
        return ResponseEntityBuilder.ok(result);
    }
}