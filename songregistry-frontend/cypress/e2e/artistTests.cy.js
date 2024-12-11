describe('Artist Page Load Test', () => {
    it('passes', () => {
      cy.visit('/artist/1')
    })
})

describe('Artist Info Test', () => {
    it('passes', () => {
        cy.visit('/artist/1')
        cy.get('#artistInfo').should('exist')
        cy.get('#artistInfo').should('be.visible')
        cy.get('#artistInfo').find('#artistImage').should('exist')
        cy.get('#artistInfo').find('#artistImage').should('be.visible')
        cy.get('#artistInfo').find('#artistName').should('exist')
        cy.get('#artistInfo').find('#artistName').should('be.visible')
        cy.get('#artistInfo').find('#artistBio').should('exist')
        cy.get('#artistInfo').find('#artistBio').should('be.visible')
    })
})

describe('Artist Albums Test', () => {
    it('passes', () => {
        cy.visit('/artist/1')
        cy.get('#artistAlbums').should('exist')
        cy.get('#artistAlbums').should('be.visible')
        cy.get('#artistAlbums').should('contain', 'Albums')
        cy.get('#artistAlbums').find('.artistAlbumCard').should('exist')
        cy.get('#artistAlbums').find('.artistAlbumCard').eq(0).should('be.visible')
        cy.get('#artistAlbums').find('.artistAlbumCard').eq(0).click()
        cy.url().should('match', /\/album\/\d+$/);
    })
})

describe('Artist Songs Test', () => {
    it('passes', () => {
        cy.visit('/artist/1')
        cy.get('#artistSongs').should('exist')
        cy.get('#artistSongs').should('be.visible')
        cy.get('#artistSongs').should('contain', 'Songs')
        cy.get('#artistSongs').find('.artistSongCard').should('exist')
        cy.get('#artistSongs').find('.artistSongCard').eq(0).should('be.visible')
        cy.get('#artistSongs').find('.artistSongCard').eq(0).click()
        cy.url().should('match', /\/song\/\d+$/);
    })
})