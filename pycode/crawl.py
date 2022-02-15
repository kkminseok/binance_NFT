# https://rubber-tree.tistory.com/88
#Selenium 용
from fileinput import close
from time import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
#BeautifutlSoup 용
from bs4 import BeautifulSoup
from collections import deque

dq = deque()

# 참고 https://greeksharifa.github.io/references/2020/10/30/python-selenium-usage/


def printall_spaceship():
    driver = webdriver.Chrome('/Users/kms/Config/chromedriver')
    url = "https://play.staratlas.com/market" 
    driver.get(url)
    driver.implicitly_wait(time_to_wait=5)

    try :
        for i in range(1,30) : 
            xpath = '/html/body/div/div/div[3]/main/div/main/div/section/section/div/div/div[2]/div/div/div[' +str(i) + ']/button/span/h3'
            elements = driver.find_element(By.XPATH,xpath)
            print(elements.text)
            dq.append(elements.text)
            
    except : 
        driver.quit()
        return
        
def search_spaceship(name) : 
    driver = webdriver.Chrome('/Users/kms/Config/chromedriver')
    #Selenium webdriver 위치지정
    #Selenium driver로 url 접속
    url = "https://play.staratlas.com/market"
    driver.get(url)
    #Selenium 접속하는 데 시간이 걸릴 수 있으므로 5초 대기
    driver.implicitly_wait(time_to_wait=5)

    #입력창
    element = driver.find_element(By.XPATH,'//*[@id="app"]/main/div/main/div/section/div[2]/div/fieldset/div/input')
    print(element)
    element.send_keys(name)
    element.send_keys(Keys.RETURN)
    driver.implicitly_wait(time_to_wait=5)
    #검색 후 대기
    clickele = driver.find_element(By.XPATH,'/html/body/div/div/div[3]/main/div/main/div/section/section/div/div/div[2]/div/div/div/button')
    clickele.click()
    clickele = driver.find_element(By.XPATH,'//*[@id="app"]/main/div/main/div/div[2]/div/div[2]/div/div[2]/div[1]/div[2]')
    clickele.click()

    data = driver.find_element(By.XPATH,'//*[@id="app"]/main/div/main/div/div[2]/div/div[1]/div/div/div[1]/span[6]/div[2]')
    driver.quit()
    return data.text

#-----------------Selenium을 이용한 제어------------#

name = 'Pearce X4'
printall_spaceship()
print(dq)
price = search_spaceship(name)
print(price)
#Selen

html = driver.page_source

#------------BeautifulSoup으로 html 정보 분석----------#

#BeautifulSoup_html을 parsing 함
soup = BeautifulSoup(html,'html.parser')

#BeautifulSoup_html 정보 분석
#BeautifulSoup_select_one()함수
#serach_result = soup.select('#__next > div > div.styles__AppContainer-jRBevD.jXJAeR > main.styles__AppMain-jqoeqn.cODPFe > div.Homestyles__Container-hJLrUh.BHoCp')
#BeautifulSoup_select()함수
#commu_list = serach_result.select('span>p')



links = []
#print("hello !!! " , serach_result)

