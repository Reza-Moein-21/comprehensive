package ir.comprehensive.service;

import ir.comprehensive.domain.model.HadisModel;

import java.util.Optional;

public interface HadisService {
    Optional<HadisModel> nextRandomHadis();
}
