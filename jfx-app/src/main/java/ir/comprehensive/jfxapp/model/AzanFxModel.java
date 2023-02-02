package ir.comprehensive.jfxapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.ToString;

@ToString
public class AzanFxModel {
    private final StringProperty sobh = new SimpleStringProperty();
    private final StringProperty tolooAftab = new SimpleStringProperty();
    private final StringProperty zohre = new SimpleStringProperty();
    private final StringProperty ghoroobAftab = new SimpleStringProperty();
    private final StringProperty maghreb = new SimpleStringProperty();
    private final StringProperty nemehShab = new SimpleStringProperty();

    public String getSobh() {
        return sobh.get();
    }

    public StringProperty sobhProperty() {
        return sobh;
    }

    public void setSobh(String sobh) {
        this.sobh.set(sobh);
    }

    public String getTolooAftab() {
        return tolooAftab.get();
    }

    public StringProperty tolooAftabProperty() {
        return tolooAftab;
    }

    public void setTolooAftab(String tolooAftab) {
        this.tolooAftab.set(tolooAftab);
    }

    public String getZohre() {
        return zohre.get();
    }

    public StringProperty zohreProperty() {
        return zohre;
    }

    public void setZohre(String zohre) {
        this.zohre.set(zohre);
    }

    public String getGhoroobAftab() {
        return ghoroobAftab.get();
    }

    public StringProperty ghoroobAftabProperty() {
        return ghoroobAftab;
    }

    public void setGhoroobAftab(String ghoroobAftab) {
        this.ghoroobAftab.set(ghoroobAftab);
    }

    public String getMaghreb() {
        return maghreb.get();
    }

    public StringProperty maghrebProperty() {
        return maghreb;
    }

    public void setMaghreb(String maghreb) {
        this.maghreb.set(maghreb);
    }

    public String getNemehShab() {
        return nemehShab.get();
    }

    public StringProperty nemehShabProperty() {
        return nemehShab;
    }

    public void setNemehShab(String nemehShab) {
        this.nemehShab.set(nemehShab);
    }
}
