package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
