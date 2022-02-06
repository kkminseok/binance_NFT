# https://rubber-tree.tistory.com/88
#Selenium 용
from time import time
from selenium import webdriver
import time
#BeautifutlSoup 용
from bs4 import BeautifulSoup

#-----------------Selenium을 이용한 제어------------#

#Selenium webdriver 위치지정
driver = webdriver.Chrome('/Users/kms/Config/chromedriver')
#Selenium driver로 url 접속
url = "https://play.staratlas.com/market/EpqNSh1XWZiCzphHeKE2rqJAEcAtJE8KSBFxnNL4KPDy"
driver.get(url)
#Selenium 접속하는 데 시간이 걸릴 수 있으므로 1초 대기
time.sleep(1)

element = driver.find_element_by_xpath('//*[@id="app"]/main/div/main/div/div[2]/div/div[1]/div/div/div[1]')
print(element)

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

