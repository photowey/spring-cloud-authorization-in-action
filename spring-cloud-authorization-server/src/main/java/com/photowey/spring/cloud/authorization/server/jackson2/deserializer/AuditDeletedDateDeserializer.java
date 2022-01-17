/*
 * Copyright © 2022 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.spring.cloud.authorization.server.jackson2.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.photowey.spring.cloud.authorization.server.jpa.audit.Audit;
import com.photowey.spring.cloud.authorization.server.jpa.audit.AuditDeletedDate;

import java.io.IOException;
import java.time.Instant;

/**
 * {@code AuditDeletedDateDeserializer}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
public class AuditDeletedDateDeserializer extends JsonDeserializer<AuditDeletedDate> {

    private static final TypeReference<Instant> INSTANT = new TypeReference<Instant>() {
    };

    @Override
    public AuditDeletedDate deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Long createdBy = readJsonNode(jsonNode, "createdBy").asLong();
        Long lastModifiedBy = readJsonNode(jsonNode, "lastModifiedBy").asLong();
        JsonNode createdDateNode = readJsonNode(jsonNode, "createdDate");
        JsonNode lastModifiedDateNode = readJsonNode(jsonNode, "lastModifiedDate");
        JsonNode deletedDateNode = readJsonNode(jsonNode, "deletedDate");

        Instant createdDate = mapper.readValue(createdDateNode.toString(), INSTANT);
        Instant lastModifiedDate = mapper.readValue(lastModifiedDateNode.toString(), INSTANT);
        Instant deletedDate = mapper.readValue(deletedDateNode.toString(), INSTANT);

        Audit audit = new Audit(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        AuditDeletedDate auditDeletedDate = new AuditDeletedDate(audit, deletedDate);

        return auditDeletedDate;

    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
