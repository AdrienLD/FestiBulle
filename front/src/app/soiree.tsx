import { Popover, PopoverTrigger, PopoverContent } from "@/components/ui/popover"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { Select, SelectTrigger, SelectValue, SelectContent, SelectItem } from "@/components/ui/select"
import { Switch } from "@/components/ui/switch"

const Soiree: React.FC = () => {
  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button className="w-[20%]">Ajouter une nouvelle fête</Button>
      </PopoverTrigger>
      <PopoverContent className="w-[600px] p-6">
        <div className="grid gap-4">
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="name">Nom</Label>
              <Input id="name" placeholder="Nom de la fête" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="date-time">Date et heure</Label>
              <Input id="date-time" type="datetime-local" />
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="address-label">Adresse</Label>
              <Input id="address-label" placeholder="Libellé de l'adresse" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="address-city">Ville</Label>
              <Input id="address-city" placeholder="Ville" />
            </div>
          </div>
          <div className="grid grid-cols-3 gap-4">
            <div className="space-y-2">
              <Label htmlFor="address-region">Région</Label>
              <Input id="address-region" placeholder="Région" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="address-postal">Code postal</Label>
              <Input id="address-postal" placeholder="Code postal" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="total-capacity">Nombre de places total</Label>
              <Input id="total-capacity" type="number" />
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="remaining-capacity">Nombre de places restantes</Label>
              <Input id="remaining-capacity" type="number" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="event-type">Type de soirée</Label>
              <Select >
                <SelectTrigger>
                  <SelectValue placeholder="Sélectionnez un type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="birthday">Anniversaire</SelectItem>
                  <SelectItem value="wedding">Mariage</SelectItem>
                  <SelectItem value="corporate">uyrutf</SelectItem>
                  <SelectItem value="other">Autre</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="paid-event">Soirée payante</Label>
              <Switch id="paid-event" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="bring-drinks">Apporter boissons et apéritifs</Label>
              <Switch id="bring-drinks" />
            </div>
          </div>
        </div>
        <div className="mt-6 flex justify-end gap-2">
          <Button variant="outline">Annuler</Button>
          <Button type="submit">Enregistrer</Button>
        </div>
      </PopoverContent>
    </Popover>
  )
}

export default Soiree