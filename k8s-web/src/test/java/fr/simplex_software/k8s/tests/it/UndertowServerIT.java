package fr.simplex_software.k8s.tests.it;

import org.apache.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

public class UndertowServerIT
{
  private static final String URL = "http://localhost:8080";

  @Test
  public void testUndertowServer()
  {
    assertThat(get(URL).then().statusCode(HttpStatus.SC_OK).extract()
      .response().body().asString()).isEqualTo("hello world");
  }
}
