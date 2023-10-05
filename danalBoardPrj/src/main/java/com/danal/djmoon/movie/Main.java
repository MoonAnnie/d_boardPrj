package com.danal.djmoon.movie;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.danal.djmoon.common.PageVo;
import com.danal.djmoon.movie.vo.MoviePageVo;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;

public class Main {

    private WebDriver driver;
    
    private static final String url = "https://flixpatrol.com/top10/netflix/world/today/full/#netflix-1";

    public void process() throws InterruptedException {
        try {
            initializeDriver();
            getDataList(null, 0, 0); //수정
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

    public List<String> getDataList(MoviePageVo bpvo, int startRow, int endRow) throws InterruptedException {
    	
    	System.setProperty("webdriver.chrome.driver", "D:\\djmoon\\dev\\setup\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        List<String> list = new ArrayList<>();

        driver.get(url);
        Thread.sleep(2000); // 페이지 로딩을 위한 대기 시간 증가 (원하는 시간으로 조절)

        boolean hasNextPage = true;
        int currentPage = 1; // 현재 페이지를 1로 초기화
        int totalDataCount = 0; // 전체 데이터 개수를 저장할 변수 추가
        while (hasNextPage) {
            List<WebElement> rankElements = driver.findElements(By.cssSelector(".tabular-nums td"));

            // 전체 데이터 개수 파악
            totalDataCount += rankElements.size();
            
            // 현재 페이지에서 10개씩 데이터를 추출
            int startIdx = (currentPage - 1) * 100;
            int endIdx = Math.min(startIdx + 100, rankElements.size());
            List<String> pageData = new ArrayList<>();
            for (int i = startIdx; i < endIdx; i++) {
                String rank = rankElements.get(i).getText();
                System.out.println("Rank 결과 출력: " + rank);
                pageData.add(rank);
            }

            list.addAll(pageData);

            int resultCount = rankElements.size();
            System.out.println("현재 페이지 결과 개수: " + resultCount);

            if (resultCount < 100) { // 만약 결과가 100개 미만이라면 다음 페이지로 이동하지 않고 종료
                hasNextPage = false;
            } else {
                // 다음 페이지로 이동
                try {
                    WebElement nextPageButton = driver.findElement(By.linkText("다음 페이지"));
                    nextPageButton.click();
                    currentPage++; // 다음 페이지로 이동하면 현재 페이지를 증가
                    // 페이지 로딩을 기다린 후에 다음 페이지로 넘어가야 합니다.
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.urlContains("/page/")); // 페이지가 변경될 때까지 대기
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    // 다음 페이지 버튼이 없으면 마지막 페이지임
                    hasNextPage = false;
                }
            }
            
        }

        // 전체 데이터 개수를 기반으로 총 페이지 수 계산
        int totalPage = (int) Math.ceil((double) totalDataCount / 100);

        // 페이징 정보를 출력 (확인용)
        System.out.println("전체 데이터 개수: " + totalDataCount);
        System.out.println("총 페이지 수: " + totalPage);
        
        driver.quit();
        return list;
    }
    
    

    public static void main(String[] args) throws InterruptedException {
        Main crawler = new Main();
        crawler.process();
    }
}
