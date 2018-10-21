package microservice;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class UserDeserializer extends JsonDeserializer<User> {

	public UserDeserializer() {
		super(User.class);
	}

}
