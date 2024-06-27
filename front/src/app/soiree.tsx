import * as React from 'react'
import { Popover, PopoverTrigger, PopoverContent } from '@/components/ui/popover'
import { Button } from '@/components/ui/button'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from '@/components/ui/select'
import { Switch } from '@/components/ui/switch'

const Soiree: React.FC = () => {
  const [ nom, setNom ] = React.useState('')
  const [ typeSoiree, setTypeSoiree ] = React.useState('')
  const [ adresseLabel, setAdresseLabel ] = React.useState('')
  const [ ville, setVille ] = React.useState('')
  const [ codePostal, setCodePostal ] = React.useState('')
  const [ nbPlacesTotal, setNbPlacesTotal ] = React.useState('')
  const [ nbPlacesRestantes, setNbPlacesRestantes ] = React.useState('')
  const [ dateTime, setDateTime ] = React.useState('')
  const [ prix, setPrix ] = React.useState('')
  const [ apportezMateriel, setApportezMateriel ] = React.useState(false)
  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button className="w-[20%]">Ajouter une nouvelle fête</Button>
      </PopoverTrigger>
      <PopoverContent className="w-[600px] p-6 align-middle">
        <div className="grid gap-4">
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="name">Nom</Label>
              <Input id="name" placeholder="Nom de la fête" value={nom} onChange={e => setNom(e.target.value)} />
            </div>
            <div className="space-y-2">
              <Label htmlFor="event-type">Type de soirée</Label>
              <Select value={typeSoiree} onValueChange={e => setTypeSoiree(e)}>
                <SelectTrigger>
                  <SelectValue placeholder="Sélectionnez un type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="birthday">Anniversaire</SelectItem>
                  <SelectItem value="wedding">Mariage</SelectItem>
                  <SelectItem value="corporate">Corporate</SelectItem>
                  <SelectItem value="other">Autre</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>
          <div className="grid grid-cols-3 gap-4">
            <div className="space-y-2">
              <Label htmlFor="address-label">Adresse</Label>
              <Input id="address-label" placeholder="Libellé de l'adresse" value={adresseLabel} onChange={e => setAdresseLabel(e.target.value)} />
            </div>
            <div className="space-y-2">
              <Label htmlFor="address-city">Ville</Label>
              <Input id="address-city" placeholder="Ville" value={ville} onChange={e => setVille(e.target.value)} />
            </div>
            <div className="space-y-2">
              <Label htmlFor="address-postal">Code postal</Label>
              <Input id="address-postal" placeholder="Code postal" value={codePostal} onChange={e => setCodePostal(e.target.value)} />
            </div>
          </div>
          <div className="grid grid-cols-3 gap-4">
            <div className="space-y-2">
              <Label htmlFor="total-capacity">Nbr de places total</Label>
              <Input id="total-capacity" type="number" value={nbPlacesTotal} onChange={e => setNbPlacesTotal(e.target.value)} />
            </div>
            <div className="space-y-2">
              <Label htmlFor="remaining-capacity">Nbr de places restantes</Label>
              <Input id="remaining-capacity" type="number" value={nbPlacesRestantes} onChange={e => setNbPlacesRestantes(e.target.value)} />
            </div>
            <div className="space-y-2 w-40">
              <Label htmlFor="bring-drinks">Besoin de matériel</Label>
              <Switch id="bring-drinks" checked={apportezMateriel} onCheckedChange={(checked: boolean) => setApportezMateriel(checked)} />
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="date-time">Date et heure</Label>
              <Input id="date-time" type="datetime-local" value={dateTime} onChange={e => setDateTime(e.target.value)} />
            </div>
            <div className="space-y-2">
              <Label htmlFor="price">Prix de la soirée</Label>
              <Input id="price" type="number" value={prix} onChange={e => setPrix(e.target.value)} />
            </div>
          </div>
          <div className="mt-6 flex justify-end gap-2">
            <Button variant="outline">Annuler</Button>
            <Button type="submit">Enregistrer</Button>
          </div>
        </div>
      </PopoverContent>
    </Popover>
  )
}

export default Soiree