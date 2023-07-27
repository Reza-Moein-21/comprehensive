package ir.comprehensive.service.internal;

import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.dao.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import ir.comprehensive.service.HadisService;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class HadisServiceImpl implements HadisService {
    private final HadisDao hadisDao;

    public HadisServiceImpl(HadisDao hadisDao) {
        this.hadisDao = hadisDao;
    }

    @Override
    public Optional<HadisModel> nextRandomHadis() {
        int nextPage = (int) (Math.random() * hadisDao.totalCount());

        return Optional.of(hadisDao
                        .findAll(PageRequestModel.of(nextPage, 1)))
                .filter(h -> nonNull(h.content()))
                .filter(h -> !h.content().isEmpty())
                .map(h -> h.content().get(0));
    }

}
