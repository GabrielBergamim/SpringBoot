package com.gabrielbergamim.cursomc.services.validation;

import com.gabrielbergamim.cursomc.domain.Cliente;
import com.gabrielbergamim.cursomc.dto.ClienteDTO;
import com.gabrielbergamim.cursomc.repositoreis.ClienteRepository;
import com.gabrielbergamim.cursomc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getNome()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}