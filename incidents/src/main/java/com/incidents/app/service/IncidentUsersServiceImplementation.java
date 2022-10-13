package com.incidents.app.service;

import com.clients.app.authentication.AuthenticationClient;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomCouldNotDeleteException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.IncidentUsers;
import com.incidents.app.repository.IncidentUsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class IncidentUsersServiceImplementation implements IncidentUsersService{

    private final IncidentUsersRepository incidentUsersRepository;
    private final IncidentService incidentService;
    private final AuthenticationClient authenticationClient;

    @Override
    public Optional<IncidentUsers> getById(Long id) {
        return incidentUsersRepository.findById(id);
    }

    @Override
    public IncidentUsers getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "incident_users", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<IncidentUsers> getAll() {
        return incidentUsersRepository.findAll();
    }

    @Override
    public List<IncidentUsers> getAllByIncidentId(Long id) {
        return incidentUsersRepository.getAllByIncidentId(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public IncidentUsers create(Long incidentId, Long userId) {
        IncidentUsers incidentUsers;
        try {
            incidentUsers = new IncidentUsers();
            incidentUsers.setIncident(incidentService.getByIdThrowException(incidentId));
            if (authenticationClient.doesExist(userId)) {
                incidentUsers.setUserId(userId);
            }
            else {
                throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
            }

            return this.save(incidentUsers);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }



    @Override
    public void delete(Long id) {
        try {
            incidentUsersRepository.delete(this.getByIdThrowException(id));
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotDeleteException(CustomStatusCode.COULD_NOT_DELETE_RECORD_IN_DB.getCode());
        }
    }

    private IncidentUsers save(IncidentUsers incidentUsers) {
        return incidentUsersRepository.save(incidentUsers);
    }
}
