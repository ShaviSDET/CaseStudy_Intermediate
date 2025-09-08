from selenium.webdriver.common.by import By
from utils.helpers import wait_for, wait_clickable, safe_click
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

class InventoryPage:
    def __init__(self, driver):
        self.driver = driver
        self.add_btn = (By.CSS_SELECTOR, "button.btn_inventory")
        self.cart_badge = (By.CLASS_NAME, "shopping_cart_badge")
        self.cart_icon = (By.ID, "shopping_cart_container")
        self.menu_btn = (By.ID, "react-burger-menu-btn")
        self.menu_visible_marker = (By.ID, "react-burger-cross-btn")   # X button shows when menu is open
        self.logout_link = (By.ID, "logout_sidebar_link")

    def is_loaded(self):
        return self.driver.title == "Swag Labs"

    def add_one_item(self):
        safe_click(self.driver, self.add_btn)

    def cart_count(self):
        els = self.driver.find_elements(*self.cart_badge)
        return int(els[0].text) if els else 0

    def open_cart(self):
        safe_click(self.driver, self.cart_icon)

    def open_menu(self):
        # click the burger, then wait for the menu animation to finish
        safe_click(self.driver, self.menu_btn)
        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located(self.menu_visible_marker)
        )

    def logout(self):
        # wait until the Logout item is clickable (after slide-in)
        safe_click(self.driver, self.logout_link)
