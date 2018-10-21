package microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final UsersRespository bookRepository;

    public AppRunner(UsersRespository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... Fetching books");
        Long id = (long) 6;
        logger.info("isbn-1234 -->" + bookRepository.findById(id));
        logger.info("isbn-4567 -->" + bookRepository.findById(id));
        logger.info("isbn-1234 -->" + bookRepository.findById(id));
        logger.info("isbn-4567 -->" + bookRepository.findById(id));
        logger.info("isbn-1234 -->" + bookRepository.findById(id));
        logger.info("isbn-1234 -->" + bookRepository.findById(id));
    }

}
