import React from 'react'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { CiCalendar } from 'react-icons/ci'
import { IoTicketOutline } from 'react-icons/io5'

export interface CardSoireeInterface {
  adresseId: number
  apportezBoissonsAperitifs: boolean
  dateHeure: string
  datePublication: string
  description: string
  estPayante: boolean
  id: number
  nbPlacesRestantes: number
  nbPlacesTotal: number
  nom: string
  organisateurId: number
  prix: number | null
  typeSoiree: string
}

interface Soiree {
  soiree: CardSoireeInterface
}

const CardSoiree: React.FC<Soiree> = ({ soiree }) => {
  return (
    <Card className="w-full max-w-md">
      <CardHeader>
        <CardTitle>Soirée Dansante</CardTitle>
        <CardDescription>
          <div className="flex items-center gap-2">
            <CiCalendar className="w-4 h-4 text-muted-foreground" />
            <span>Vendredi 30 juin 2023</span>
            <span>20h - 2h</span>
          </div>
        </CardDescription>
      </CardHeader>
      <CardContent className="grid gap-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
            <span>Payant</span>
          </div>
          <div className="flex items-center gap-2">
            <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
            <span>20 places restantes</span>
          </div>
        </div>
        <div className="flex items-center gap-2">
          <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
          <span>123 Rue des Fêtes, 75000 Paris</span>
        </div>
        <div className="flex items-center gap-2">
          <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
          <span>Soirée dansante</span>
        </div>
        <div className="flex items-center gap-2">
          <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
          <span>Musique électronique</span>
        </div>
        <div className="flex items-center gap-2">
          <IoTicketOutline className="w-4 h-4 text-muted-foreground" />
          <span>Entrée 10€</span>
        </div>
      </CardContent>
    </Card>
  )
}

export default CardSoiree