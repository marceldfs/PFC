package novo.isutc.pfc.dao;

import java.util.List;

import novo.isutc.pfc.bean.Versao;

public interface VersaoDao extends AbstractDao<Versao, Integer> {
    void saveVersao(Versao versao);
    List<Versao> findVersaos(Integer numeroversao);
}
