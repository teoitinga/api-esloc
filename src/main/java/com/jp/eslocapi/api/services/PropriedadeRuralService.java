package com.jp.eslocapi.api.services;

import com.jp.eslocapi.api.dto.PropriedadeRuralMinDtoPost;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.entities.PropriedadeRural;

public interface PropriedadeRuralService {

	PropriedadeRuralMinDtoPost save(PropriedadeRuralMinDtoPost post);

	PropriedadeRuralMinDtoPost toPropriedadeRuralMinDtoPost(PropriedadeRural saved);

	PropriedadeRural toPropriedadeRural(PropriedadeRuralMinDtoPost post);

	String gerarCodigoPropriedade(String key);

	PropriedadeRural findPropriedadeRural(Persona proprietario, String nomeDaPropriedade);

	PropriedadeRural save(PropriedadeRural propriedade);

}
