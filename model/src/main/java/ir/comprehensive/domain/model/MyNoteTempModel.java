package ir.comprehensive.domain.model;

import ir.comprehensive.domain.model.base.DomainModel;

public record MyNoteTempModel(
        Long id,
        String title,
        String description,
        MyNoteModel myNote

) implements DomainModel<Long> {
}
