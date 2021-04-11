import { element, by, ElementFinder } from 'protractor';

export class LivreurComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-livreur div table .btn-danger'));
  title = element.all(by.css('jhi-livreur div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class LivreurUpdatePage {
  pageTitle = element(by.id('jhi-livreur-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  firstnameInput = element(by.id('field_firstname'));
  lastnameInput = element(by.id('field_lastname'));
  mailInput = element(by.id('field_mail'));
  phoneInput = element(by.id('field_phone'));
  reviewsInput = element(by.id('field_reviews'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setFirstnameInput(firstname: string): Promise<void> {
    await this.firstnameInput.sendKeys(firstname);
  }

  async getFirstnameInput(): Promise<string> {
    return await this.firstnameInput.getAttribute('value');
  }

  async setLastnameInput(lastname: string): Promise<void> {
    await this.lastnameInput.sendKeys(lastname);
  }

  async getLastnameInput(): Promise<string> {
    return await this.lastnameInput.getAttribute('value');
  }

  async setMailInput(mail: string): Promise<void> {
    await this.mailInput.sendKeys(mail);
  }

  async getMailInput(): Promise<string> {
    return await this.mailInput.getAttribute('value');
  }

  async setPhoneInput(phone: string): Promise<void> {
    await this.phoneInput.sendKeys(phone);
  }

  async getPhoneInput(): Promise<string> {
    return await this.phoneInput.getAttribute('value');
  }

  async setReviewsInput(reviews: string): Promise<void> {
    await this.reviewsInput.sendKeys(reviews);
  }

  async getReviewsInput(): Promise<string> {
    return await this.reviewsInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class LivreurDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-livreur-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-livreur'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
