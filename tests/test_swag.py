from pages.login_page import LoginPage
from pages.inventory_page import InventoryPage

def test_swag_flow(driver):
    base_url = "https://www.saucedemo.com/"

    # 1) Launch
    lp = LoginPage(driver)
    lp.open(base_url)

    # 2) Verify SWAG LABS (page title)
    assert lp.title() == "Swag Labs"

    # Login (valid creds)
    lp.login("standard_user", "secret_sauce")

    inv = InventoryPage(driver)
    assert inv.is_loaded()

    # 3) Add one item
    inv.add_one_item()

    # 4) Verify cart count and open cart
    assert inv.cart_count() == 1
    inv.open_cart()

    # 5) Logout
    inv.open_menu()
    inv.logout()

    # Back on login page (title still "Swag Labs")
    assert lp.title() == "Swag Labs"
