package ru.spbstu.telematics.testlabs.LabThree;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;

//кодировка - windows-1251

public class TestPostmanRequest {

    @Test //запросить расширенную информацию о пользователе по id: имя, фамилия, скрыт ли профиль пользователя, пол,
            //дата рождения, статус, количество подписчиков, сравнить status-code и содержимое ответа
    public void testUsersGet() {
        when()
                .get("https://api.vk.com/method/users.get?user_ids=559841696&fields=followers_count,bdate,status,sex&" +
                        "access_token=419f38af41ba75caa953e80e1b4cdfa8719c61fe437bbda7fee80e7e1ccaab1585ed373db6fe1399a84ba&v=5.103")
                .then()
                .statusCode(200)
                .body("response.id", hasItem(559841696),
                        "response.first_name", hasItem("Анастасия"),
                        "response.last_name", hasItem("Ляхова"),
                        "response.is_closed", hasItem(false),
                        "response.sex", hasItem(1),
                        "response.bdate", hasItem("9.10.2005"),
                        "response.status", hasItem("у меня лапки~"),
                        "response.followers_count", hasItem(13));
    }

    @Test //запросить информацию о заданном сообществе по короткому имени: название, id, является ли сообщество закрытым
            //тип сообщества, открыта ли стена, статус, сравнить status-code и содержимое ответа
    public void testGroupGet() {
        when()
                .get("https://api.vk.com/method/groups.getById?group_id=animalpic&fields=name,wall,status&" +
                        "access_token=419f38af41ba75caa953e80e1b4cdfa8719c61fe437bbda7fee80e7e1ccaab1585ed373db6fe1399a84ba&v=5.103")
                .then()
                .statusCode(200)
                .body("response.id", hasItem(148523451),
                        "response.name", hasItem("животные картинки"),
                        "response.screen_name", hasItem("animalpic"),
                        "response.is_closed", hasItem( 0),
                        "response.type", hasItem("page"),
                        "response.wall", hasItem(2),
                        "response.status", hasItem("\uD83D\uDC3E\uD83D\uDC3E"));
    }
}
