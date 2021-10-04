package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	

	
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver","/home/brunoroman/dev/devops/java/seleniumDrivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.200.127:8080/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via selenuiuem");
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		} finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void deveSalvarTarefaSemData() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via selenuiuem");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void deveSalvarTarefaComDataPassada() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste via selenuiuem");
			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			driver.quit();
		}
	}
	
}
