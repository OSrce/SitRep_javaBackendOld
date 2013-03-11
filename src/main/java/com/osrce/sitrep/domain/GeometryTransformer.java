package com.osrce.sitrep.domain;

import flexjson.transformer.AbstractTransformer;

public class GeometryTransformer extends AbstractTransformer {

	@Override
	public void transform(Object object) {
        getContext().writeQuoted((String) object.toString());
    }
	
}

