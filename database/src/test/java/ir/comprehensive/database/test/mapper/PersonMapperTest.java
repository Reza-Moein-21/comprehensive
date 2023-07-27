package ir.comprehensive.database.test.mapper;

import ir.comprehensive.database.internal.mapper.PersonMapper;
import org.jooq.generated.tables.records.PersonRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonMapperTest {
    PersonMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(PersonMapper.class);
    }

    @Test
    void givingNullRecord_recordToModel_shouldGetNull() {
        assertThat(mapper.recordToModel(null)).isNull();
    }

    @Test
    void givingFirstNameAndLastName_recordToModel_shouldGetExpectedTitleField() {
        var sampleRecord = new PersonRecord();
        sampleRecord.setFirstName("FN");
        sampleRecord.setLastName("LN");

        var model = mapper.recordToModel(sampleRecord);
        assertThat(model.title()).isEqualTo(sampleRecord.getFirstName() + " " + sampleRecord.getLastName());
        assertThat(model.fullName()).isEqualTo(model.title());
    }
}
