package ir.comprehensive.service.provider;

import ir.comprehensive.database.model.PageRequestModel;
import ir.comprehensive.database.service.HadisDao;
import ir.comprehensive.domain.model.HadisModel;
import ir.comprehensive.service.HadisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class HadisServiceImpl implements HadisService {
    private final HadisDao hadisDaoService;

    @Override
    public Optional<HadisModel> nextRandomHadis() {
        int nextPage = (int) (Math.random() * hadisDaoService.totalCount());

        return Optional.of(hadisDaoService
                        .findAll(PageRequestModel.of(nextPage, 1)))
                .filter(h -> nonNull(h.content()))
                .filter(h -> !h.content().isEmpty())
                .map(h -> h.content().get(0));
    }

}