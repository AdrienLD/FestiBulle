import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from '@/components/ui/select'
import React, { useState } from 'react'
import { Checkbox } from '@/components/ui/checkbox'
import { filtrerSoirees } from './API'

export interface Filtre {
    nom?: string
    nbPersonnes?: string
    typeSoiree?: string
    estPayante?: boolean | null
    }

const Filtres = ({setFiltre}: { setFiltre: (filtre: Filtre) => void }) => {
  const [ ville, setVille ] = useState('')
  const [ type, setType ] = useState('')
  const [ people, setPeople ] = useState('')
  const [ name, setName ] = useState('')
  const [ freePaid, setFreePaid ] = useState(false)

  const filtrer = async () => {
    /*
    private String nom;
    private Integer adresseId;
    private String typeSoiree;
    private Integer nbPersonnes;
    private Boolean estPayante;
    */

    const filtre: Filtre = {
        typeSoiree: type !== 'TOUS' ? type : '',
        nom: name,
        nbPersonnes: people,
        estPayante: freePaid ? true : null,
    }
    setFiltre(filtre)
  }

  return (
    <div className="flex flex-wrap bg-card p-4 rounded-lg shadow-lg w-full">
      <form className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 xl:grid-cols-6 gap-4 justify-between w-full">
        <div>
          <Label htmlFor="city">Ville</Label>
          <Input
            id="city"
            type="text"
            className="w-full"
            value={ville}
            onChange={(e) => setVille(e.target.value)}
            placeholder="Filtrer une ville"
          />
        </div>
        <div>
          <Label htmlFor="type">Type de soirée</Label>
          <Select 
            value={type} 
            onValueChange={(e) => setType(e)}
          >
            <SelectTrigger className="w-full">
              <SelectValue placeholder="Type de soirée"/>
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="TOUS">Tous</SelectItem>
              <SelectItem value="CLASSIQUE">Classique</SelectItem>
              <SelectItem value="JEUX_SOCIETE">Jeux de sociétés</SelectItem>
              <SelectItem value="JEUX_VIDEO">Jeux vidéo</SelectItem>
              <SelectItem value="AU">Autres</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="people">Nombre de personnes</Label>
          <Input id="people" type="number" className="w-full" placeholder="Entrez un nombre" value={people} onChange={(e) => setPeople(e.target.value)}/>
        </div>
        <div>
          <Label htmlFor="city">Nom de la soirée</Label>
          <Input
            id="name"
            type="text"
            className="w-full"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Filtrer une ville"
          />
        </div>
        <div>
          <Label htmlFor="free-paid">Gratuit</Label>
          <br/>
          <Checkbox id="terms" checked={freePaid} onCheckedChange={(checked: boolean) => setFreePaid(checked)}/>
        </div>
        <div className="flex h-full w-full items-center ">
          <Button type="submit" className="w-full" variant="secondary" onClick={(e) => { e.preventDefault(); filtrer() }}>
                    Appliquer les filtres
          </Button>
        </div>
      </form>
    </div>
  )
}

export default Filtres
