package ru.spbstu.telematics.testlabs.LabThree;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;

//��������� - windows-1251

public class TestPostmanRequest {

    @Test //��������� ����������� ���������� � ������������ �� id: ���, �������, ����� �� ������� ������������, ���,
            //���� ��������, ������, ���������� �����������, �������� status-code � ���������� ������
    public void testUsersGet() {
        when()
                .get("https://api.vk.com/method/users.get?user_ids=559841696&fields=followers_count,bdate,status,sex&" +
                        "access_token=419f38af41ba75caa953e80e1b4cdfa8719c61fe437bbda7fee80e7e1ccaab1585ed373db6fe1399a84ba&v=5.103")
                .then()
                .statusCode(200)
                .body("response.id", hasItem(559841696),
                        "response.first_name", hasItem("���������"),
                        "response.last_name", hasItem("������"),
                        "response.is_closed", hasItem(false),
                        "response.sex", hasItem(1),
                        "response.bdate", hasItem("9.10.2005"),
                        "response.status", hasItem("� ���� �����~"),
                        "response.followers_count", hasItem(13));
    }

    @Test //��������� ���������� � �������� ���������� �� ��������� �����: ��������, id, �������� �� ���������� ��������
            //��� ����������, ������� �� �����, ������, �������� status-code � ���������� ������
    public void testGroupGet() {
        when()
                .get("https://api.vk.com/method/groups.getById?group_id=animalpic&fields=name,wall,status&" +
                        "access_token=419f38af41ba75caa953e80e1b4cdfa8719c61fe437bbda7fee80e7e1ccaab1585ed373db6fe1399a84ba&v=5.103")
                .then()
                .statusCode(200)
                .body("response.id", hasItem(148523451),
                        "response.name", hasItem("�������� ��������"),
                        "response.screen_name", hasItem("animalpic"),
                        "response.is_closed", hasItem( 0),
                        "response.type", hasItem("page"),
                        "response.wall", hasItem(2),
                        "response.status", hasItem("\uD83D\uDC3E\uD83D\uDC3E"));
    }
}
