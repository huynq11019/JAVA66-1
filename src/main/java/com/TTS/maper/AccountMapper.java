package com.TTS.maper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.TTS.DTO.AccountDTO;
import com.TTS.Entity.Account;
import com.TTS.Entity.Authrority;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	public AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	@Mappings({ @Mapping(source = "entity", target = "authrority", qualifiedByName = "fromAuthrority"),
//		   @Mapping(target = "orderString", ignore = true),		
	})
	AccountDTO toDto(Account entity);

	@Mappings({ @Mapping(target = "passwordHash",source = "dto.password") 
	,@Mapping(source = "dto", target = "authrority", qualifiedByName = "toAuthrority"),
			})
	Account toEntity(AccountDTO dto);

	default List<AccountDTO> toListDto(List<Account> entityList) {
		List<AccountDTO> dtoList = new ArrayList<>();
		dtoList = entityList.stream().map(this::toDto).collect(Collectors.toList());
		return dtoList;
	}

	default Account formID(Integer id) {
		if (id == null) {
			return null;
		}
		Account acc = new Account();
		acc.setId(id);
		return acc;
	}

	@Named("toAuthrority")
	default Set<Authrority> authrority(AccountDTO dto) {
		Set<Authrority> authrority = new HashSet<>();
		if (dto.getAuthrority() != null) {
			authrority = dto.getAuthrority().stream().map(string -> {
				Authrority auth = new Authrority();
				auth.setName(string);
				return auth;
			}).collect(Collectors.toSet());
		}

		return authrority;
	}

	@Named("fromAuthrority")
	default Set<String> authrorityToString(Account ac) {
		Set<String> authoritiesString = new HashSet<>();
		if (ac.getAuthrority() != null) {
			authoritiesString = ac.getAuthrority().stream().map(Authrority::getName).collect(Collectors.toSet());
		}
		return authoritiesString;

	}
}
