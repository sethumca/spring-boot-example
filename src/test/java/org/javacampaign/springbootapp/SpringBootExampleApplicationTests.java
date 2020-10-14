package org.javacampaign.springbootapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.javacampaign.springbootapp.repo.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootExampleApplicationTests {
    @Value("${local.server.port}")
    int port;
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void test() throws Exception {
        {
            // Select
            List<City> cities = restTemplate.exchange("http://localhost:" + port, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<City>>() {
            }).getBody();
            assertThat(cities.size(), is(3));
            assertThat(cities.get(0).getId(), is(1));
            assertThat(cities.get(0).getName(), is("Tokyo"));
            assertThat(cities.get(1).getId(), is(2));
            assertThat(cities.get(1).getName(), is("New York"));
            assertThat(cities.get(2).getId(), is(3));
            assertThat(cities.get(2).getName(), is("London"));
        }

        {
            // Update
            List<City> cities = restTemplate.exchange("http://localhost:" + port + "/update?name=Kyoto", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<City>>() {
            }).getBody();
            assertThat(cities.size(), is(3));
            assertThat(cities.get(0).getId(), is(1));
            assertThat(cities.get(0).getName(), is("Kyoto"));
            assertThat(cities.get(1).getId(), is(2));
            assertThat(cities.get(1).getName(), is("New York"));
            assertThat(cities.get(2).getId(), is(3));
            assertThat(cities.get(2).getName(), is("London"));
        }
    }
}
