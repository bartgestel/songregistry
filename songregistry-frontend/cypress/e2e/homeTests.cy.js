describe('Homepage Load Test', () => {
  it('passes', () => {
    cy.visit('/')
  })
})

describe('Navbar Test', () => {
  it('passes', () => {
    cy.visit('/') 
    cy.get('#navbar').should('be.visible')
    cy.get('#navbar').should('contain', 'Home')
    cy.get('#navbar').should('contain', 'Artists')
    cy.get('#navbar').should('contain', 'Albums')
    cy.get('#navbar').should('contain', 'Genres')
    cy.get('#navbar').find('#searchBar').should('exist')
    cy.get('#navbar').find('#searchBar').should('be.visible')
  })
})

describe('Searchbar Test', () => {
  it('passes', () => {
    cy.visit('/')
    cy.get('#searchBar').should('exist')
    cy.get('#searchBar').should('be.visible')
    cy.get('#searchBarInput').should('exist')
    cy.get('#searchBarInput').should('be.visible')
    cy.get('#searchBarInput').type('tyler')
    cy.get('#searchBarInput').should('have.value', 'tyler')
    cy.get('#searchResults').should('exist')
    cy.get('#searchResults').should('be.visible')
    cy.get('#searchResults').find('.searchResult').should('exist')
    cy.get('#searchResults').find('.searchResult').should('be.visible')
    cy.get('#searchResults').find('.searchResult').eq(0).click()
    cy.url().should('match', /\/artist\/\d+$/);
  })
})

describe('Home Artists Test', () => {
  it('passes', () => {
    cy.visit('/')
    cy.get('#homeArtists').should('exist')
    cy.get('#homeArtists').should('be.visible')
    cy.get('#homeArtists').should('contain', 'Artists')
    cy.get('#homeArtists').find('.artistCard').should('exist')
    cy.get('#homeArtists').find('.artistCard').eq(0).should('be.visible')
    cy.get('#homeArtists').find('.artistCard').eq(0).click()
    cy.url().should('match', /\/artist\/\d+$/);

  })
})

describe('Home Albums Test', () => {
  it('passes', () => {
    cy.visit('/')
    cy.get('#homeAlbums').should('exist')
    cy.get('#homeAlbums').should('be.visible')
    cy.get('#homeAlbums').should('contain', 'Albums')
    cy.get('#homeAlbums').find('.albumCard').should('exist')
    cy.get('#homeAlbums').find('.albumCard').eq(0).should('be.visible')
    cy.get('#homeAlbums').find('.albumCard').eq(0).click()
    cy.url().should('match', /\/album\/\d+$/);
  })
})
