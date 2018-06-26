package com.icongtai.geo.service.impl;

import com.icongtai.geo.model.Echo;
import com.icongtai.geo.service.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public Echo getE() {
        return new Echo();
    }
}
