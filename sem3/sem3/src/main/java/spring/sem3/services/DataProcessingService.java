package spring.sem3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sem3.domain.User;
import spring.sem3.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    private UserRepository repository;
    private NotificationService notificationService;

    public DataProcessingService(UserRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public UserRepository getRepository() {
        return repository;
    }

    public List<User> sortUsersByAge(List<User> users) {
        notificationService.sendNotification("Выполнена операция - Сортировка по возрасту");
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        notificationService.sendNotification("Выполнена операция - Фильтр по возрасту: старше " + age);
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        notificationService.sendNotification("Выполнена операция - Расчет среднего возраста");
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }
}
