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
package com.photowey.spring.cloud.authorization.server.jpa.audit;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

/**
 * {@code AuditDeletedDate}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Embeddable
@AccessType(AccessType.Type.FIELD)
public class AuditDeletedDate extends Audit {

    @Column(name = "DeletedDate")
    private Instant deletedDate;

    public AuditDeletedDate() {

    }

    public AuditDeletedDate(Audit audit, Instant deletedDate) {
        super(audit);
        this.deletedDate = deletedDate;
    }

    public AuditDeletedDate(Long createdBy, Instant createdDate, Long lastModifiedBy, Instant lastModifiedDate, Instant deletedDate) {
        super(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.deletedDate = deletedDate;
    }

    public Instant getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Instant deletedDate) {
        this.deletedDate = deletedDate;
    }

}
