package io.github.rojae.authsignupweb.repository;

import io.github.rojae.authsignupweb.common.domain.SignupStepUUID;
import org.springframework.data.repository.CrudRepository;

public interface SignupStepUUIDRepository extends CrudRepository<SignupStepUUID, String> {
}