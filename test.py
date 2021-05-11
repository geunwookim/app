# naver login macro

from selenium import webdriver

driver = webdriver.Chrome("C:/Users/dlogixs/Desktop/test/chromedriver_win32/chromedriver.exe") # chrom ver 90.0.4430

driver.get("http://naver.com")
xpath = '//a[@class="link_login"]'
driver.find_element_by_xpath(xpath).click()

xpath_id = '//input[@id="id"]'
xpath_pw = '//input[@id="pw"]'
my_id = 'kimgeunwoo'
my_pw = '1234'
driver.find_element_by_xpath(xpath_id).send_keys(my_id)
driver.find_element_by_xpath(xpath_pw).send_keys(my_pw)

xpath_login = '//input[@id="log.login"]'
driver.find_element_by_xpath(xpath_login).click()
