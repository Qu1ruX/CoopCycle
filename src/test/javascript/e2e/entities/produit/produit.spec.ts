import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProduitComponentsPage, ProduitDeleteDialog, ProduitUpdatePage } from './produit.page-object';

const expect = chai.expect;

describe('Produit e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let produitComponentsPage: ProduitComponentsPage;
  let produitUpdatePage: ProduitUpdatePage;
  let produitDeleteDialog: ProduitDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Produits', async () => {
    await navBarPage.goToEntity('produit');
    produitComponentsPage = new ProduitComponentsPage();
    await browser.wait(ec.visibilityOf(produitComponentsPage.title), 5000);
    expect(await produitComponentsPage.getTitle()).to.eq('coopCycleApp.produit.home.title');
    await browser.wait(ec.or(ec.visibilityOf(produitComponentsPage.entities), ec.visibilityOf(produitComponentsPage.noResult)), 1000);
  });

  it('should load create Produit page', async () => {
    await produitComponentsPage.clickOnCreateButton();
    produitUpdatePage = new ProduitUpdatePage();
    expect(await produitUpdatePage.getPageTitle()).to.eq('coopCycleApp.produit.home.createOrEditLabel');
    await produitUpdatePage.cancel();
  });

  it('should create and save Produits', async () => {
    const nbButtonsBeforeCreate = await produitComponentsPage.countDeleteButtons();

    await produitComponentsPage.clickOnCreateButton();

    await promise.all([
      produitUpdatePage.setNameInput('Zfy'),
      produitUpdatePage.setDescriptionInput('description'),
      produitUpdatePage.setPriceInput('5'),
      produitUpdatePage.commerceSelectLastOption(),
    ]);

    expect(await produitUpdatePage.getNameInput()).to.eq('Zfy', 'Expected Name value to be equals to Zfy');
    expect(await produitUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await produitUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');

    await produitUpdatePage.save();
    expect(await produitUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Produit', async () => {
    const nbButtonsBeforeDelete = await produitComponentsPage.countDeleteButtons();
    await produitComponentsPage.clickOnLastDeleteButton();

    produitDeleteDialog = new ProduitDeleteDialog();
    expect(await produitDeleteDialog.getDialogTitle()).to.eq('coopCycleApp.produit.delete.question');
    await produitDeleteDialog.clickOnConfirmButton();

    expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
