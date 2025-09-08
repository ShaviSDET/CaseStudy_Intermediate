from selenium.webdriver.common.by import By
from utils.helpers import wait_for

class LoginPage:
    def __init__(self, driver):
        self.driver = driver
        self.user = (By.ID, "user-name")
        self.passw = (By.ID, "password")
        self.login_btn = (By.ID, "login-button")

    def open(self, url):
        self.driver.get(url)

    def title(self):
        return self.driver.title  # "Swag Labs"

    def login(self, username, password):
        wait_for(self.driver, self.user).clear()
        self.driver.find_element(*self.user).send_keys(username)
        self.driver.find_element(*self.passw).clear()
        self.driver.find_element(*self.passw).send_keys(password)
        self.driver.find_element(*self.login_btn).click()
