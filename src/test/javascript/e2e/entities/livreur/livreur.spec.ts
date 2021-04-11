import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LivreurComponentsPage, LivreurDeleteDialog, LivreurUpdatePage } from './livreur.page-object';

const expect = chai.expect;

describe('Livreur e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let livreurComponentsPage: LivreurComponentsPage;
  let livreurUpdatePage: LivreurUpdatePage;
  let livreurDeleteDialog: LivreurDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Livreurs', async () => {
    await navBarPage.goToEntity('livreur');
    livreurComponentsPage = new LivreurComponentsPage();
    await browser.wait(ec.visibilityOf(livreurComponentsPage.title), 5000);
    expect(await livreurComponentsPage.getTitle()).to.eq('coopCycleApp.livreur.home.title');
    await browser.wait(ec.or(ec.visibilityOf(livreurComponentsPage.entities), ec.visibilityOf(livreurComponentsPage.noResult)), 1000);
  });

  it('should load create Livreur page', async () => {
    await livreurComponentsPage.clickOnCreateButton();
    livreurUpdatePage = new LivreurUpdatePage();
    expect(await livreurUpdatePage.getPageTitle()).to.eq('coopCycleApp.livreur.home.createOrEditLabel');
    await livreurUpdatePage.cancel();
  });

  it('should create and save Livreurs', async () => {
    const nbButtonsBeforeCreate = await livreurComponentsPage.countDeleteButtons();

    await livreurComponentsPage.clickOnCreateButton();

    await promise.all([
      livreurUpdatePage.setFirstnameInput('Ktd'),
      livreurUpdatePage.setLastnameInput('Pqstl'),
      livreurUpdatePage.setMailInput('H2JGIT@0RamO1.mRFf'),
      livreurUpdatePage.setPhoneInput('phone'),
      livreurUpdatePage.setReviewsInput('5'),
    ]);

    expect(await livreurUpdatePage.getFirstnameInput()).to.eq('Ktd', 'Expected Firstname value to be equals to Ktd');
    expect(await livreurUpdatePage.getLastnameInput()).to.eq('Pqstl', 'Expected Lastname value to be equals to Pqstl');
    expect(await livreurUpdatePage.getMailInput()).to.eq('H2JGIT@0RamO1.mRFf', 'Expected Mail value to be equals to H2JGIT@0RamO1.mRFf');
    expect(await livreurUpdatePage.getPhoneInput()).to.eq('phone', 'Expected Phone value to be equals to phone');
    expect(await livreurUpdatePage.getReviewsInput()).to.eq('5', 'Expected reviews value to be equals to 5');

    await livreurUpdatePage.save();
    expect(await livreurUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await livreurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Livreur', async () => {
    const nbButtonsBeforeDelete = await livreurComponentsPage.countDeleteButtons();
    await livreurComponentsPage.clickOnLastDeleteButton();

    livreurDeleteDialog = new LivreurDeleteDialog();
    expect(await livreurDeleteDialog.getDialogTitle()).to.eq('coopCycleApp.livreur.delete.question');
    await livreurDeleteDialog.clickOnConfirmButton();

    expect(await livreurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
