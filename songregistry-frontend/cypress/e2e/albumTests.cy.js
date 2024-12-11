describe('Album Page Load Test', () => {
    it('passes', () => {
      cy.visit('/album/1')
    })
})

describe('Album Info Test', () => {
    it('passes', () => {
        cy.visit('/album/1')
        cy.get('#albumInfo').should('exist')
        cy.get('#albumInfo').should('be.visible')
        cy.get('#albumInfo').find('#albumImage').should('exist')
        cy.get('#albumInfo').find('#albumImage').should('be.visible')
        cy.get('#albumInfo').find('#albumName').should('exist')
        cy.get('#albumInfo').find('#albumName').should('be.visible')
        cy.get('#albumInfo').find('#albumArtists').should('exist')
        cy.get('#albumInfo').find('#albumArtists').should('be.visible')
        cy.get('#albumInfo').find('#albumRating').should('exist')
        cy.get('#albumInfo').find('#albumRating').should('be.visible')
    })
})