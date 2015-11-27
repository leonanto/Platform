/**
 * Copyright 2015 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.adapter.domain.smartmetering.application.mapping;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import com.alliander.osgp.dto.valueobjects.smartmetering.PeriodicMeterReadsRequestData;

public class PeriodicMeterReadsConverter
extends
BidirectionalConverter<com.alliander.osgp.dto.valueobjects.smartmetering.PeriodicMeterReadsRequestData, PeriodicMeterReadsRequestData> {

    @Override
    public PeriodicMeterReadsRequestData convertTo(
            final com.alliander.osgp.dto.valueobjects.smartmetering.PeriodicMeterReadsRequestData source,
            final Type<PeriodicMeterReadsRequestData> destinationType) {
        return new PeriodicMeterReadsRequestData(source.getDeviceIdentification(),
                com.alliander.osgp.dto.valueobjects.smartmetering.PeriodType.valueOf(source.getPeriodType().name()),
                source.getBeginDate(), source.getEndDate());
    }

    @Override
    public com.alliander.osgp.dto.valueobjects.smartmetering.PeriodicMeterReadsRequestData convertFrom(
            final PeriodicMeterReadsRequestData source,
            final Type<com.alliander.osgp.dto.valueobjects.smartmetering.PeriodicMeterReadsRequestData> destinationType) {
        return new PeriodicMeterReadsRequestData(source.getDeviceIdentification(),
                com.alliander.osgp.dto.valueobjects.smartmetering.PeriodType.valueOf(source.getPeriodType().name()),
                source.getBeginDate(), source.getEndDate());
    }

}
