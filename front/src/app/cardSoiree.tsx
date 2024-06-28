import React from 'react'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { CiCalendar } from 'react-icons/ci'
import { IoTicketOutline } from 'react-icons/io5'
import { MdOutlinePlace } from 'react-icons/md'
import { MdOutlineEventSeat } from 'react-icons/md'
import { PiBeerBottleFill } from 'react-icons/pi'
import { MdEuro } from 'react-icons/md'
import { IoMdInformationCircleOutline } from 'react-icons/io'
import Soiree from './soiree'
import { getAdresse, getUser } from './API'

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
  const [ formattedDate, setFormattedDate ] = React.useState('')
  const [ adresse, setAdresse ] = React.useState('')

  React.useEffect(() => {
    const fetchAdresse = async () => {
      const adresse = await getAdresse(soiree.adresseId)
      setAdresse(adresse.ville)
      const user = await getUser(soiree.organisateurId)
      console.log(user)
    }
    fetchAdresse()
  }, [])

  React.useEffect(() => {
    const date = new Date(soiree.dateHeure)
    setFormattedDate(date.toLocaleDateString('fr-FR', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric'
    }))
  }, [ soiree.dateHeure ])

  return (
    <Card className="w-full max-w-md ">
      <CardHeader>
        <CardTitle>{soiree.nom}</CardTitle>
        <CardDescription>
          {soiree.typeSoiree}
          <div className="flex items-center gap-2">
            <CiCalendar className="w-4 h-4 text-muted-foreground" />
            <span>{formattedDate}</span>
          </div>
        </CardDescription>
      </CardHeader>
      <CardContent className="grid gap-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <MdOutlineEventSeat className="w-4 h-4 text-muted-foreground" />
            <span>{soiree.nbPlacesRestantes} places restantes</span>
          </div>
        </div>
        <div className="flex items-center gap-2">
          <MdOutlinePlace className="w-4 h-4 text-muted-foreground" />
          <span>{adresse}</span>
        </div>
        <div className="flex items-center gap-2">
          <PiBeerBottleFill className="w-4 h-4 text-muted-foreground" />
          <span>{soiree.apportezBoissonsAperitifs ? "Besoin d'apporter" : "Pas besoin d'apporter"}</span>
        </div>
        <div className="flex items-center gap-2">
          <MdEuro className="w-4 h-4 text-muted-foreground" />
          <span>{soiree.prix === null ? 'Gratuit' : soiree.prix + '€'}</span>
        </div>
        <div className="flex items-center gap-2">
          <IoMdInformationCircleOutline className="w-4 h-4 text-muted-foreground" />
          <span>{soiree.description}</span>
        </div>
      </CardContent>
    </Card>
  )
}

export default CardSoiree