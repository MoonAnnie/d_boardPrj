package com.danal.djmoon.movie;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Detail {

    private WebDriver driver;
    
    private static final String url = "https://flixpatrol.com/top10/netflix/world/today/full/#netflix-1";

    public void process() throws InterruptedException {
        try {
            initializeDriver();
            getDataList();
        } finally {
            closeDriver();
            //driver.quit(); //추가
        }
    }

    private void initializeDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\djmoon\\dev\\setup\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("WebDriver 초기화 중 오류가 발생했습니다.");
        }
    }

    private void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public List<String> getDataList() throws InterruptedException {
    	
    	try {
            System.setProperty("webdriver.chrome.driver", "D:\\djmoon\\dev\\setup\\chromedriver-win64\\chromedriver.exe");
            log.debug("driver 정보 가져오기::" + driver);
            driver = new ChromeDriver();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("WebDriver 초기화 중 오류가 발생했습니다.");
        }
    	
        List<String> list = new ArrayList<>();
        
        driver.get(url);
        Thread.sleep(2000); // 페이지 로딩을 위한 대기 시간 증가 (원하는 시간으로 조절)

        // 원하는 선택자로 요소 선택
        //List<WebElement> rankElements = driver.findElements(By.cssSelector(".table-td"));
        List<WebElement> rankElements = driver.findElements(By.cssSelector(".tabular-nums td"));

        for (WebElement rankElement : rankElements) {
            String rank = rankElement.getText();
            System.out.println("Rank 결과 출력: " + rank);
            list.add(rank); //list에 rank 값 추가하는 코드
        }

        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        Detail crawler = new Detail();
        crawler.process();
    }
}
