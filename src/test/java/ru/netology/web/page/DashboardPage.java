package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
  private ElementsCollection cards = $$(".list__item");
  private String balanceStart = "баланс: ";
  private String balanceFinish = " р.";
  private SelenideElement heading = $("[data-test-id=dashboard]");


  public DashboardPage() {
    heading.shouldBe(visible);
  }


  public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
    cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).$("button").click();
    return new TransferPage();
  }

  public int getCardBalance01() {
    return getCardBalance("01");
  }

  public int getCardBalance02() {
    return getCardBalance("02");
  }

  public int getCardBalance(String id) {
    var text = cards.findBy(text(id)).text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    var start = text.indexOf(balanceStart);
    var finish = text.indexOf(balanceFinish);
    var value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);

  }
}
