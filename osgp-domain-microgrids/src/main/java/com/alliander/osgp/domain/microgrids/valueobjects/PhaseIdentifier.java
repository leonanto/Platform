/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.domain.microgrids.valueobjects;

import java.io.Serializable;

public class PhaseIdentifier implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6870534272621261131L;

    private final int id;
    private final String name;

    public PhaseIdentifier(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
