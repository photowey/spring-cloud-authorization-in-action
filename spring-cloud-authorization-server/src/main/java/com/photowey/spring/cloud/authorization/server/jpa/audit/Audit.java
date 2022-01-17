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

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * {@code Audit}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Setter
@Getter
@MappedSuperclass
@Embeddable
@AccessType(AccessType.Type.FIELD)
public class Audit {

    @CreatedBy
    @Column(name = "CreatedBy")
    private Long createdBy;

    @CreatedDate
    @Column(name = "CreatedDate")
    private Instant createdDate;

    @LastModifiedBy
    @Column(name = "UpdatedBy")
    private Long lastModifiedBy;

    @LastModifiedDate
    @Column(name = "UpdatedDate")
    private Instant lastModifiedDate;

    public Audit() {

    }

    public Audit(Long createdBy, Instant createdDate, Long lastModifiedBy, Instant lastModifiedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Audit(Audit audit) {
        this.createdBy = audit.createdBy;
        this.createdDate = audit.createdDate;
        this.lastModifiedBy = audit.lastModifiedBy;
        this.lastModifiedDate = audit.lastModifiedDate;
    }

}