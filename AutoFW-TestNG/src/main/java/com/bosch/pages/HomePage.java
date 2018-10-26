package com.bosch.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.bosch.models.ResultModel;
import com.bosch.utils.WebActions;

public class HomePage extends WebActions{
	 
	public HomePage(WebDriver driver) {
		super(driver);		
		PageFactory.initElements(driver, HomePage.class);
	}

	 @FindBy(how = How.XPATH, using = "//input[@id='lst-ib']")
	    private WebElement textboxSearch;
	 
	 @FindBy(how = How.XPATH, using = "//input[@name='btnK']")
	    private WebElement buttonSearch;
	
	public List<ResultModel> search(String keyword){
		List<ResultModel> listRes = new ArrayList<>();
		listRes.add(sendKeys(textboxSearch, "[Search] textbox", keyword));
		listRes.add(clickOnWebElement(buttonSearch, "[Search] button"));
		return listRes;
	}

}
